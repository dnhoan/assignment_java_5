package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.InterceptorAuth;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private InterceptorAuth interceptorAuth;
	@Autowired
	private AdminInterceptor adminInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorAuth).addPathPatterns("/admin/orders/*", "/admin/categories/*",
				"/admin/products/*", "/admin/users/*", "/shop/cart/*", "/shop/orders/*");
		
		registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/orders/*","/admin/ordersStore/*", "/admin/categories/*",
				"/admin/products/*", "/admin/users/*");
	}

}
