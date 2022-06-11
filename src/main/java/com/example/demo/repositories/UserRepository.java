package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	
	public Users findByEmail(String email);
}
