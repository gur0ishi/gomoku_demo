package com.example.gomoku_demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOConfig {
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
