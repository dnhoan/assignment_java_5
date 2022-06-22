package com.example.demo.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailModel {
	String from = "Cửa hàng bán quần áo";
//	String to;
//	List<String> cc = new ArrayList<String>();
	String subject = "Thông báo thêm sản phẩm hàng loạt bằng file Excel";
	List<String> bodies = new ArrayList<String>();;
	List<String> bcc = new ArrayList<String>();
	List<File> files = new ArrayList<>();
	public MailModel(List<String> bodies, List<String> bcc, List<File> files) {
		super();
		this.bodies = bodies;
		this.bcc = bcc;
		this.files = files;
	}
	
}
