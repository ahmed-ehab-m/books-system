package com.global.book.config;

import org.jspecify.annotations.Nullable;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class WebConfig implements WebMvcConfigurer {

//    private final Validator getValidator;
//
//    WebConfig(Validator getValidator) {
//        this.getValidator = getValidator;
//    }
		@Bean
		public AuditorAware<String> auditorAware()
		
		{
			return new AuditorAwarImpl();
		}
		@Bean
		public MessageSource messageSource()
		{
			ReloadableResourceBundleMessageSource messageSource= new ReloadableResourceBundleMessageSource();
			messageSource.setBasename("classpath:bundle/messages");
			messageSource.setDefaultEncoding("UTF-8");
			return messageSource;
		}
		@Bean
		@Override
		public LocalValidatorFactoryBean getValidator() {
			LocalValidatorFactoryBean bean=new LocalValidatorFactoryBean();
			bean.setValidationMessageSource(messageSource());
			return bean;
		}
}
