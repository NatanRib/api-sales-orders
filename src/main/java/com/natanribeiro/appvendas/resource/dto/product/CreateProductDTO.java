package com.natanribeiro.appvendas.resource.dto.product;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.natanribeiro.appvendas.domain.entity.Product;

public class CreateProductDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "The field description cannot be null or empty")
	private String description;
	
	@NotNull(message = "The field price cannot be null")
	private Double price;
	
	public CreateProductDTO(String description, Double price) {
		super();
		this.description = description;
		this.price = price;
	}
	
	public CreateProductDTO() {}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}
	
	public Product toProduct() {
		return new Product(null, description, price);
	}
}
