package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.demo.entities.Categories;
import com.example.demo.repositories.CategoryRepository;

@Service
public class CategoryService implements ICrudService<Categories, Integer>{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void index(Model model, int size, int page) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Categories> categories = this.categoryRepository.findAll(pageable);
		model.addAttribute("items", categories);
		if (!model.containsAttribute("category")) {
	        model.addAttribute("category", new Categories());
	    } 
		this.configViews(model,"categories/create.jsp");
	}

	@Override
	public void create(Categories t) {
		t.setName(t.getName().trim());
		this.categoryRepository.save(t);
	}

	@Override
	public void update(Integer k, Categories t) {
		this.categoryRepository.save(t);
	}

	@Override
	public void delete(Categories t) {	
		this.categoryRepository.delete(t);
	}

	@Override
	public void edit(Model model, Categories t, int size, int page) {
		if (!model.containsAttribute("category")) {
			model.addAttribute("category", t);
		} 
		Pageable pageable = PageRequest.of(page, size);
		Page<Categories> categories = this.categoryRepository.findAll(pageable);
		model.addAttribute("items", categories);
		this.configViews(model, "categories/edit.jsp");
	}
	
	public void validateStringFiedLength(BindingResult bindingResult, Categories category) {
		int lengthName = category.getName().trim().length();
		if(lengthName < 6 || lengthName > 225) {
			bindingResult.rejectValue("name", "length.category.name", "Tên danh mục từ 6 - 225 ký tự");
		}
	}
	
	private  void configViews(Model model, String form) {
		model.addAttribute("content", "admin/content.jsp");
		model.addAttribute("menu", "admin/menu.jsp");
		model.addAttribute("form", form);
		model.addAttribute("table", "categories/table.jsp");
	}

	@Override
	public Categories getById(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
