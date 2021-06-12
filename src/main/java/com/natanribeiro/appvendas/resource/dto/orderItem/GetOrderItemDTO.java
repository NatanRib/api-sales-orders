package com.natanribeiro.appvendas.resource.dto.orderItem;

import java.io.Serializable;

import com.natanribeiro.appvendas.domain.entity.OrderItem;

public class GetOrderItemDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer productId;
	private String productDescription;
	private Integer quantity;
	private Double price;
	
	public GetOrderItemDTO() {}

	public GetOrderItemDTO(Integer productId, 
			String productDdescription, Integer quantity, Double price) {
		super();
		this.productId = productId;
		this.productDescription = productDdescription; 
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getproductId() {
		return productId;
	}
	
	public String getProductDescription() {
		return productDescription;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getPrice() {
		return price;
	}
	
	public static GetOrderItemDTO fromOrderItem(OrderItem oi) {
		return new GetOrderItemDTO(oi.getProduct().getId(),
				oi.getProduct().getDescription(), oi.getQuantity(), oi.getPrice());
	}
}