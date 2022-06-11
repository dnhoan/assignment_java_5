// Generated with g9.

package com.example.demo.entities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity(name = "products")
public class Products implements Serializable {

	/** Primary key. */
	protected static final String PK = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255)
	@NotNull(message = "Nhập tên sản phẩm")
	private String name;

	@Column(nullable = false, precision = 10)
	@NotNull(message = "Nhập tồn kho")
	@Min(value = 0, message = "Tồn kho phải lớn hơn hoặc bằng 0")
	private int stock;

	@NotNull(message = "Nhập giá sản phẩm")
	@Min(value = 0, message = "Giá sản phẩm lớn hơn bằng 0")
	@Column(nullable = false, precision = 10)
	private int price;

	@NotNull(message = "Nhập màu sắc sản phẩm")
	@Column(nullable = false, length = 255)
	private String color;

	@NotNull(message = "Vui lòng nhập kích cỡ")
	@Column(nullable = false, length = 255)
	private String size;
	
	@Column(nullable = true, length = 255)
	private String image;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@NotNull(message = "Thiếu danh mục")
	private Categories category;

	@Column(nullable = false, length = 1)
	private String status;

	@Column(length = 225)
	private String description;
	

}
