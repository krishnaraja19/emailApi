package com.refactor.maillauncher.controller.configurator;

import org.springframework.context.annotation.Bean;

import com.refactor.maillauncher.services.SimpleEmailValidator;

public class EmailConfigurator {
	
	@Bean
	public SimpleEmailValidator simpleEmailValidator() {
		return new SimpleEmailValidator();
	}

}
