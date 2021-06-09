package com.natanribeiro.appvendas.resource.dto.order;

import com.natanribeiro.appvendas.domain.entity.OrderItem;

public class GetOrderItemDTO {
	
	private Integer id;
	private Integer productId;
	private String productDescription;
	private Integer quantity;
	
	public GetOrderItemDTO() {}
	
	public GetOrderItemDTO(Integer id, Integer productId, String productDescription, Integer quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.productDescription = productDescription;
		this.quantity = quantity;
	}
	
	public Integer getId() {
		return id;
	}

	public Integer getProductId() {
		return productId;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public static GetOrderItemDTO fromOrderItem(OrderItem oi) {
		return new GetOrderItemDTO(oi.getId(), oi.getProduct().getId(),
				oi.getProduct().getDescription(), oi.getQuantity());
	}
}
