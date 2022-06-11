package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
	
}
