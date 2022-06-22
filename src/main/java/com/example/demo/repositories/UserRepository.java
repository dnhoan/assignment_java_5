package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	
	@Query(value = "select u.email from users u where u.id = :id")
	List<String> getEmailById(int id);
	
	public Users findByEmail(String email);
}
