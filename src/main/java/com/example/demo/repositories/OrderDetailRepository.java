package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	
//	@Query(value = "select * from OrderDetail o where o.orderId = :orderId")
	List<OrderDetail> findByOrderIdEquals(int orderId);
}
