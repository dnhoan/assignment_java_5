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
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.demo.constants.Constants;

import lombok.Data;

@Data
@Entity(name="users")
public class Users implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    
    @NotNull(message = "Vui lòng nhập họ và tên")
    @Column(nullable=false, length=255)
    private String fullname;
    
    @NotNull(message = "Vui lòng chọn giới tính")
    @Column(nullable=false, precision=10)
    private int gender;
    
    @NotNull(message = "Vui lòng nhập số điện thoại")
    @NotBlank(message = "Vui lòng nhập số điện thoại")
    @Pattern(regexp = Constants.P_PHONE_NUMBER, message = "Sai định dạng điện thoại")
    @Column(name="phone_number", nullable=false, length=255)
    private String phoneNumber;
    
    @NotNull(message = "Vui lòng nhập họ và tên")
    @Column(nullable=false, length=255)
    private String address;
    
    @NotNull(message = "Vui lòng nhập địa chỉ email")
    @NotBlank(message = "Vui lòng nhập địa chỉ email")
    @Pattern(regexp = Constants.P_EMAIL, message = "Sai định dạng email")
    @Column(nullable=false, length=255)
    private String email;
    
    @Column(nullable=false, length=255)
    private String password;
    
    @Column(length=255)
    private String image;
    
    @Column(nullable=false, precision=10)
    private int role;
    
    @Column(nullable=false, length=5)
    private String status;
}
