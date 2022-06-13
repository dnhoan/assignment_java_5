package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.OrderDetailRepository;

@Service
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	public void addToOrder() {
		
	}
	public void deleteToOrder() {
		
	}
}
