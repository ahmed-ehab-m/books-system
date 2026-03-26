package com.global.book.controller;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.global.book.service.AuthorService;
import com.global.book.service.FileUploadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor // instead of autowired (constructor injection)

public class FileUploadController {
	
	private final FileUploadService fileUploadService;
	
	@PostMapping("/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam Long id,@RequestParam String pathType,
			@RequestParam MultipartFile file)
	{
		
		String fileName= fileUploadService.storeFile(fileUploadService.convertMultiPartFileToFile(file), id, pathType);
		
		return ResponseEntity.ok(fileName);
	}
}
