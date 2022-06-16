// Generated with g9.

package com.example.demo.entities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name="order_detail_store")
public class OrderDetailStore implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(nullable=false, precision=10)
    private int amount;
    
    @ManyToOne
    @JoinColumn(name="order_store_id", nullable=false)
    private OrdersStore ordersStore;
    
    
    @OneToOne
    @JoinColumn(name="product_id", nullable=false)
    private Products product;
    
    @Column(nullable=false, precision=12)
    private float price;


}
