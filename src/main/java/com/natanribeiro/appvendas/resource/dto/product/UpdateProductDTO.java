package com.natanribeiro.appvendas.resource.dto.product;

import java.io.Serializable;

import com.natanribeiro.appvendas.domain.entity.Product;

public class UpdateProductDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String description;
	private Double price;
	
	public UpdateProductDTO(String description, Double price) {
		super();
		this.description = description;
		this.price = price;
	}
	
	public UpdateProductDTO() {}

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
