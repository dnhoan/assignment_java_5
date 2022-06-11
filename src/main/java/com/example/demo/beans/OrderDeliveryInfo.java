package com.example.demo.beans;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.demo.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveryInfo {
	@NotBlank(message = "Nhập địa chỉ giao hàng")
    @NotNull(message = "Nhập địa chỉ giao hàng")
    private String deliveryAddress;
    
    @NotBlank(message = "Nhập số điện thoại giao hàng")
    @NotNull(message = "Nhập số điện thoại giao hàng")
    @Pattern(regexp = Constants.P_PHONE_NUMBER, message = "Sai định dạng số điện thoại")
    private String phoneNumber;
    
    private String note;
}
