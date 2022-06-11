package com.example.demo.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.demo.beans.UserLogin;
import com.example.demo.beans.UserRegister;
import com.example.demo.constants.Constants;
import com.example.demo.entities.Users;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.EncryptUtils;

@Service
public class AccountService {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private UserService userSerice;
	
	public boolean login(UserLogin userLogin, BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {
			String email = userLogin.getEmail();
			String password = userLogin.getPassword();
			Users user = this.userRepository.findByEmail(email);
			if(user == null) {
				bindingResult.rejectValue("email", "check.userLogin.email", "Email không tồn tại");
			} else if (!EncryptUtils.check(password, user.getPassword())) {
				bindingResult.rejectValue("password", "check.userLogin.password", "Sai mật khẩu");
			} else {
				this.httpSession.setAttribute("user", user);
				this.httpSession.setAttribute("role", user.getRole());
				return true;
			}
		}
		return false;
	}
	
	public void create(UserRegister u) {
		Users user = new Users();
		user.setAddress(u.getAddress());
		user.setEmail(u.getEmail());
		user.setFullname(u.getFullname());
		user.setGender(u.getGender());
		user.setPhoneNumber(u.getPhoneNumber());
		user.setRole(0);
		user.setStatus("1");
		user.setPassword(u.getPassword());
		this.userSerice.create(user);
	}
	
	public void validatePassword(BindingResult bindingResult, UserRegister userRegister) {
		String password = userRegister.getPassword();
		if (password.isEmpty()) {
			bindingResult.rejectValue("password", "NotBlack.user.passoword", "Vui lòng nhập mật khẩu");
		} else if (!password.matches(Constants.P_PASSWORD)) {
			bindingResult.rejectValue("password", "Pattern.user.passoword",
					"Mật khẩu bao gồm số và chữ và từ 6-225 ký tự");
		} else if (!userRegister.getPassword().equals(password)){
			bindingResult.rejectValue("confirmPassword", "confirm.user.confirmPassword",
					"Xác nhận mật khẩu sai");
		}
	}

	public void validateLengthString(BindingResult bindingResult, UserRegister user) {
		int lengthFullName = user.getFullname().trim().length();
		if (lengthFullName < 5 || lengthFullName > 225) {
			bindingResult.rejectValue("fullname", "length.user.fullname", "Họ và tên trong khoảng 5-225 ký tự");
		}
		int lengthAddress = user.getAddress().trim().length();
		if (lengthAddress < 5 || lengthAddress > 225) {
			bindingResult.rejectValue("address", "length.user.address", "Địa chỉ trong khoảng 5-225 ký tự");
		}
	}
	public void checkEmailExist(BindingResult bindingResult, UserRegister user) {
		if(!bindingResult.hasErrors()) {
			String email = user.getEmail();
			if (email.matches(Constants.P_EMAIL)) {
				Users u = this.userRepository.findByEmail(email);
				if (u != null) {
					bindingResult.rejectValue("email", "exist.user.email", "Địa chỉ đã tồn tại");
				}
			}
		}
	}
}
