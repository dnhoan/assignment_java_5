package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
	@Query(name = "select o from Orders o where o.id= :id Order")
	Orders getOrderById(int id);
	public Page<Orders> findByUserIdOrderByCtimeDesc(int userId, Pageable pageable);
	public Page<Orders> findByOrderByCtimeDesc(Pageable pageable);
}	
