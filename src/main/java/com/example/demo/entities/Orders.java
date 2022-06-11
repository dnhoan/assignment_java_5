// Generated with g9.

package com.example.demo.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="orders", indexes={@Index(name="orders_order_id_IX", columnList="order_id", unique=true)})
public class Orders implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";


    @Column(name="order_id", unique=true, nullable=false, length=50)
    private String orderId;
    
    @ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
    
    @Column(name="order_status", nullable=false, precision=10)
    private int orderStatus;
    @Column(nullable=false, length=1)
    private String status;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="delivery_address", nullable=false, length=225)
    private String deliveryAddress;
    @Column(name="phone_number", nullable=false, length=15)
    private String phoneNumber;
    @Column(name="total_money", nullable=false, precision=22)
    private double totalMoney;
    private String note;
    @Column(name="cancel_note")
    private String cancelNote;
    @Column(nullable=false)
    private Date ctime;
    private Date mtime;

	
	@OneToMany( mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;
   

}
