package com.example.demo.services;

import org.springframework.ui.Model;

public interface ICrudService<T, K> {
	void index(Model model, int size, int page);
	void create(T t);
	void update(K k, T t);
	void delete(T t);
	void edit(Model model, T t, int size, int page);
	T getById(K k);
}
