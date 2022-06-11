package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.demo.constants.Constants;
import com.example.demo.entities.Products;
import com.example.demo.entities.Users;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.EncryptUtils;

@Service
public class UserService implements ICrudService<Users, Integer> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void index(Model model, int size, int page) {
		this.showTable(model, size, page);
		this.configViews(model, "users/create.jsp");
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new Users());
		}
	}

	@Override
	public void create(Users t) {
		String password = t.getPassword();
		String encrypted = EncryptUtils.encrypt(password);
		t.setPassword(encrypted);
		this.userRepository.save(t);
	}

	@Override
	public void update(Integer k, Users t) {
		Users currentUser = this.userRepository.getReferenceById(k);
		t.setPassword(currentUser.getPassword());
		this.userRepository.save(t);
	}

	@Override
	public void delete(Users t) {
		this.userRepository.delete(t);
	}

	@Override
	public void edit(Model model, Users t, int size, int page) {
		this.configViews(model, "users/edit.jsp");
		this.showTable(model, size, page);
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", t);
		}
	}

	@Override
	public Users getById(Integer k) {
		// TODO Auto-generated method stub
		return this.userRepository.getById(k);
	}

	public void validatePassword(BindingResult bindingResult, Users user) {
		String password = user.getPassword();
		if (password.isEmpty()) {
			bindingResult.rejectValue("password", "NotBlack.user.passoword", "Vui lòng nhập mật khẩu");
		} else if (!password.matches(Constants.P_PASSWORD)) {
			bindingResult.rejectValue("password", "Pattern.user.passoword",
					"Mật khẩu bao gồm số và chữ và từ 6-225 ký tự");
		}

	}

	public void validateLengthString(BindingResult bindingResult, Users user) {
		int lengthFullName = user.getFullname().trim().length();
		if (lengthFullName < 5 || lengthFullName > 225) {
			bindingResult.rejectValue("fullname", "length.user.fullname", "Họ và tên trong khoảng 5-225 ký tự");
		}
		int lengthAddress = user.getAddress().trim().length();
		if (lengthAddress < 5 || lengthAddress > 225) {
			bindingResult.rejectValue("address", "length.user.address", "Địa chỉ trong khoảng 5-225 ký tự");
		}
	}
	public void checkEmailExist(BindingResult bindingResult, Users user) {
		String email = user.getEmail();
		if (email.matches(Constants.P_EMAIL)) {
			Users u = this.userRepository.findByEmail(email);
			if (u != null) {
				bindingResult.rejectValue("email", "exist.user.email", "Địa chỉ đã tồn tại");
			}
		}
	}

	public void checkEmailWhenUpdate(BindingResult bindingResult, Users user, int id) {
		String email = user.getEmail();
		Users currentUser = this.userRepository.getReferenceById(id);
		if (!currentUser.getEmail().equals(email)) {
			if (email.matches(Constants.P_EMAIL)) {
				Users u = this.userRepository.findByEmail(email);
				if (u != null) {
					bindingResult.rejectValue("email", "exist.user.email", "Địa chỉ đã tồn tại");
				}
			}
		}
	}


	private void configViews(Model model, String form) {
		model.addAttribute("content", "admin/content.jsp");
		model.addAttribute("menu", "admin/menu.jsp");
		model.addAttribute("form", form);
		model.addAttribute("table", "users/table.jsp");
	}

	private void showTable(Model model, int size, int page) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Users> items = this.userRepository.findAll(pageable);
		model.addAttribute("items", items);
	}
}
