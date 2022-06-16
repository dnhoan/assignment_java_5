package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.OrdersStore;

public interface OrderStoreRepository extends JpaRepository<OrdersStore, Integer> {
	@Query(name = "select o from OrdersStore o where o.id= :id Order")
	OrdersStore getOrderById(int id);
	public Page<OrdersStore> findByOrderByCtimeDesc(Pageable pageable);
}	
