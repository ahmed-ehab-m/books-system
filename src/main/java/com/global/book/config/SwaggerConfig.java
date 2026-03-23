package com.global.book.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

  
	@Bean
	public GroupedOpenApi bookManagementApi()
	{
		String packagesToScan[]= {"com.global.book"};
		return GroupedOpenApi.builder()
				.group("Book Api")
				.packagesToScan(packagesToScan)
//				.addOperationCustomizer(appTokenHeaderParam())
				.build();
	}
//	@Bean
//	public OperationCustomizer appTokenHeaderParam()
//	{
//		return (io.swagger.v3.oas.models.Operation operation ,HandlerMethod handlerMethod)->
//		{
//			Parameter headerParameter =new Parameter().in(ParameterIn.HEADER.toString()).required(false)
//					.schema(new StringSchema)
//		}
//	}
}
