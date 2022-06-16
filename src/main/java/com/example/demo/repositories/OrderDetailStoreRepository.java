package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.OrderDetailStore;
import com.example.demo.entities.OrdersStore;


public interface OrderDetailStoreRepository extends JpaRepository<OrderDetailStore, Integer> {
	
//	@Query(value = "select * from OrderDetailStore o where o.orderStoreId = :orderStoreId")
	List<OrderDetailStore> findByOrdersStoreEquals(OrdersStore ordersStore);
}
