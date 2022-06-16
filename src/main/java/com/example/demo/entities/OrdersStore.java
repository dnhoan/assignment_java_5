// Generated with g9.

package com.example.demo.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
@Data
@Entity(name = "orders_store")
public class OrdersStore implements Serializable {

	/** Primary key. */
	protected static final String PK = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "order_status", nullable = false, precision = 10)
	private int orderStatus;

	@Column(name = "order_id", nullable = false, length = 50)
	private String orderId;

	@Column(name = "name_customer", nullable = false, length = 225)
	private String nameCustomer;
	
	@Column(name = "delivery_address", nullable = false, length = 225)
	private String deliveryAddress;
	
	@Column(name = "phone_number", nullable = false, length = 15)
	private String phoneNumber;
	
	private String note;
	
	@Column(name = "cancel_note")
	private String cancelNote;
	
	@Column(name = "total_money")
	private double totalMoney;
	
	@Column(nullable = false)
	private Date ctime;
	
	private Date mtime;

	@OneToMany( mappedBy = "ordersStore", fetch = FetchType.LAZY)
	private List<OrderDetailStore> orderDetailStores;
}
