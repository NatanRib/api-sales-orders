package com.natanribeiro.appvendas.resource.dto.order;

import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.domain.entity.OrderItem;
import com.natanribeiro.appvendas.domain.entity.Product;

public class CreateOrderItemDTO {
	
	private Integer orderId;
	private Integer productId;
	private Integer quantity;
	private Double price;
	
	public CreateOrderItemDTO() {}

	public CreateOrderItemDTO(Integer productId, Integer quantity, Double price) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getProductId() {
		return productId;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public Double getPrice() {
		return price;
	}

	public OrderItem toOrderItem() {
		return new OrderItem(null, new Order(orderId, null, null),new Product(productId, null, null), quantity, price);
	}
}
