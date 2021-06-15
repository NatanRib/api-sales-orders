package com.natanribeiro.appvendas.resource.dto.orderItem;

import javax.validation.constraints.NotNull;

import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.domain.entity.OrderItem;
import com.natanribeiro.appvendas.domain.entity.Product;

public class CreateOrderItemDTO {
	
	private Integer orderId;
	@NotNull(message="Field 'productId' cannot be null")
	private Integer productId;
	@NotNull(message="Field 'quantity' cannot be null")
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
