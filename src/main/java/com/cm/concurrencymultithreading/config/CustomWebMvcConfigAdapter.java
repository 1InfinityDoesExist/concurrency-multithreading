package com.cm.concurrencymultithreading.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cm.concurrencymultithreading.interceptors.LoggingInterceptor;

@Configuration
@EnableWebMvc
public class CustomWebMvcConfigAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggingInterceptor());
	}
}
