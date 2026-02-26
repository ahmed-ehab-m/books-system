package com.global.book.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwarImpl implements  AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		// fixed user for test
		return Optional.of("test user");
	}

}
