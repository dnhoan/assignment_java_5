package com.example.demo.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.demo.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {

    @NotNull(message = "Vui lòng nhập họ và tên")
    private String fullname;
    
    @NotNull(message = "Vui lòng chọn giới tính")
    private int gender;
    
    @NotNull(message = "Vui lòng nhập số điện thoại")
    @NotBlank(message = "Vui lòng nhập số điện thoại")
    @Pattern(regexp = Constants.P_PHONE_NUMBER, message = "Sai định dạng điện thoại")
    private String phoneNumber;
    
    @NotNull(message = "Vui lòng nhập họ và tên")
    private String address;
    
    @NotNull(message = "Vui lòng nhập địa chỉ email")
    @NotBlank(message = "Vui lòng nhập địa chỉ email")
    @Pattern(regexp = Constants.P_EMAIL, message = "Sai định dạng email")
    private String email;
    
    private String password;
    
    private String confirmPassword;
}
