package com.global.book.config;

import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;


@Configuration
@EnableScheduling
@EnableAsync
@ConditionalOnProperty(name="scheduler.enabled",matchIfMissing = true)
@EnableSchedulerLock(defaultLockAtMostFor = "10m")

public class SchedulerConfig implements AsyncConfigurer{

    private final LocalValidatorFactoryBean getValidator;

    SchedulerConfig(LocalValidatorFactoryBean getValidator) {
        this.getValidator = getValidator;
    }
	@Bean
	public LockProvider lockProvider(DataSource dataSource)
	{
		return new JdbcTemplateLockProvider(
			JdbcTemplateLockProvider.Configuration.builder()
			.withJdbcTemplate(new JdbcTemplate(dataSource))
			.usingDbTime().build()); 
	}
	
	// multithread config
	// custom configuration for function level
	@Bean(name = "threadPoolTaskExecutor")
	public Executor asyncExecutor()
	{
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(50);
		executor.setThreadNamePrefix("AsynchThread::");
		executor.initialize();
		return executor;
	}
	
	// application level
	@Override
	public @Nullable Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(50);
		executor.setThreadNamePrefix("AsynchThread::");
		executor.initialize();
		return executor;
	}
}
