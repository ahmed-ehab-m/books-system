package com.global.book.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.util.IOUtils;
import com.global.book.entity.Author;
import com.global.book.error.FileStorageException;
import com.global.book.repository.AuthorRepo;
import com.google.api.client.auth.oauth2.Credential;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor // instead of autowired (constructor injection)

@Log4j2 // give me a log variable to use it in code
public class FileUploadService {

    private final Executor threadPoolTaskExecutor;
	
    private Path fileStorageLocation;
     
//    @Value("${file.upload.base-path}")
    private  String basePath;
    
    private final AuthorService authorService;
    
    ///////////////aws 
//    @Value("${aws-s3.bucket}")
    private String awsBucketName;
    
    private final AmazonS3 amazonS3;
   
    
    // google
    
//    @Value("${google.storage.bucket-name}")
    private String googleBucketName;

//    @Value("${google.storage.project-id}")
    private String projectId="";
    
//    @Value("${google.storage.credentials.path}")
    private String credentialPath="";
    
//////////////////////////////////////

//    FileUploadService(Executor threadPoolTaskExecutor) {
//        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
//    }
    /////////////////////////
    
    public String cloudUploadFile(MultipartFile file ,Long id ,String pathType)
    {
    	String fileName=null;
    	if(file.getContentType().contains("image"))
    	{
    		fileName=id+"_"+UUID.randomUUID()+".jpg";
    	} else {
    		fileName=id+ file.getOriginalFilename();

    	}
    	String uniqueFileName=pathType +fileName;
    	try
    	{
    		awsUploadObject(uniqueFileName ,file);
    		// update in our db
        	updateImagePath(id,pathType,pathType+"/"+fileName);

    	}catch (Exception e) {
    		throw new FileStorageException("file upload failed"+e);
		}
//    	String downloadUri=basePath + pathType + fileName;
    	return fileName;
    }
    
    //////////////////////////
    
    public void awsUploadObject(final String uniqueFileName ,final MultipartFile multipartFile)
    {
    	log.info("uploading file"+uniqueFileName);
    	try
    	{
    		ObjectMetadata meta=new ObjectMetadata();
//    		meta.setContentLength(IOUtils.toByteArray(multipartFile.getInputStream()).length);
    		meta.setContentLength(multipartFile.getSize());	
    		final PutObjectRequest putObjectRequest =new PutObjectRequest(awsBucketName, uniqueFileName, 
    				multipartFile.getInputStream(),meta)
    				.withCannedAcl(CannedAccessControlList.PublicRead);
    		PutObjectResult result= amazonS3.putObject(putObjectRequest);
        	log.info("file uploaded successfully"+ result.toString());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /////////////////////////
    
    public void awsDeleteObject(String fileUrl)
    
    {
    	final DeleteObjectRequest req= new DeleteObjectRequest(awsBucketName,fileUrl);
    	amazonS3.deleteObject(req);
    	log.info("file deleted from bucket "+ awsBucketName + "as"+fileUrl);
    }
    
    
    /////////////////////////
    
	public File convertMultiPartFileToFile(final MultipartFile multipartFile)
	{
		final File file=new File(multipartFile.getOriginalFilename());
		try(final FileOutputStream outputStream = new FileOutputStream(file))
		{
				outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex)
		{
			log.error("Error Converting the multi part file to file = ",ex.getMessage() );
		}
		return file;
	}
	/////////////////////////
	
	private void updateImagePath(Long id,String pathType,String imagePath)
	{
		if(pathType.contains("authors"))
		{
			Author author=authorService.findById(id);
			author.setImagePath(imagePath);
			authorService.update(author);
		}
	}
	
    /////////////////////////

	
	public String storeFile(File file,Long id,String pathType)
	{
		// get the full location in hard disk
		fileStorageLocation=Paths.get(basePath+pathType).toAbsolutePath().normalize();
		try
		{
			Files.createDirectories(fileStorageLocation);
		}catch (Exception e) {
			throw new FileStorageException("could not create the directory where the uploaded files"
					+ " will be stored"
					+ e);
		}
		
		// create the file name
		String fileName =org.springframework.util.StringUtils.cleanPath(id+"-"+file.getName());
		
		// save in hard disk in the path created
		try
		{
 			if(fileName.contains(".."))
			{
				throw new FileStorageException("sorry! file name contains invalid path sequence"+ fileName);
			}
			
			// file storage location => the location of the folder will contains the files in hardDisk
			// resolve() => take the file name and add it to the path of the container folder to 
			// create the full path
			Path targetLocation=fileStorageLocation.resolve(fileName);
			// create input stream as a reading channel from the file to be ready to copy the bytes
				InputStream targetStream=new FileInputStream(file);
			//  take the bytes from the stream and print it into the location
				Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
				updateImagePath(id,pathType,pathType+"/"+fileName);
			return fileName;
			
		}catch (Exception e) {
			throw new FileStorageException("could not store file"+ fileName + "please try again"+e);

		}
	}
	
	////// GOOGLE////////////////
	
	public void googleUploadObject(String projectId,String objectName ,MultipartFile file) throws IOException
	{    
		// first read credential file = json file (authentication file)
		// getsourceasSrream => mean that the json file in resources folder
		InputStream inputStream =getClass().getResourceAsStream(credentialPath);
		Credentials credentials =GoogleCredentials.fromStream(inputStream);
		
		// talk to storage and give it that =>
		// 1=> the credentials
		// 2=> the project id
		// getservice=> open secure connection between my server and GCP (Google Servers) 
		Storage storage=StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build()
				.getService();
		
		// blobid=> determine the location of file will be stored
		BlobId blobId= BlobId.of(googleBucketName, objectName);
		// blob info => meta data 
		// getcontentinfo => to make GCP understand type of file 
		// to when frontend request image , google display it as image not unknown file
		BlobInfo blobInfo=BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
		// take meta data and data channel 
		// start to take file from request to put it on GCP
		storage.create(blobInfo, file.getInputStream());
		log.info("file uploaded to bucket"+googleBucketName+"as"+objectName);
		}
	///////////////////////
	public void googleDeleteObject(String imagePath) throws IOException
	{
		InputStream inputStream =getClass().getResourceAsStream(credentialPath);
		Credentials credentials =GoogleCredentials.fromStream(inputStream);
		Storage storage=StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build()
				.getService();
		BlobId blobId= BlobId.of(googleBucketName, imagePath);
		storage.delete(blobId);
		log.info("file deleted from bucket"+googleBucketName+"as"+imagePath);

	}
	
}
