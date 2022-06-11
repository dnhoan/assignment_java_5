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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import lombok.Data;
@Data
@Entity(name="order_detail")
public class OrderDetail implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";


    @Column(nullable=false, precision=10)
    private int amount;
    @Column(nullable=false, precision=12)
    private float price;
    
    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Orders order;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    
    @OneToOne
    @JoinColumn(name="product_id", nullable=false)
    private Products product;

}
