package com.example.demo.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
	@NotNull(message = "Vui lòng nhập email")
	@NotBlank(message = "Vui lòng nhập email")
	private String email;
	@NotNull(message = "Vui lòng nhập mật khẩu")
	@NotBlank(message = "Vui lòng nhập mật khẩu")
	private String password;
}
