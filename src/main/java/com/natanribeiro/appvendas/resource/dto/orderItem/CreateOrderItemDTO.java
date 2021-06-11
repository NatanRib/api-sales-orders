package com.natanribeiro.appvendas.resource.dto.orderItem;

import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.domain.entity.OrderItem;
import com.natanribeiro.appvendas.domain.entity.Product;

public class CreateOrderItemDTO {
	
	private Integer orderId;
	private Integer productId;
	private Integer quantity;
	
	public CreateOrderItemDTO() {}

	public CreateOrderItemDTO(Integer productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Integer getProductId() {
		return productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public OrderItem toOrderItem() {
		return new OrderItem(null, new Order(orderId, null, null),new Product(productId, null, null), quantity);
	}
}
