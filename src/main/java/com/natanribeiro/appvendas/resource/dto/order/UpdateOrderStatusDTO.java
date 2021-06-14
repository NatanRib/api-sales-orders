package com.natanribeiro.appvendas.resource.dto.order;

import com.natanribeiro.appvendas.enums.OrderStatus;

public class UpdateOrderStatusDTO {

	private String status;
	
	public UpdateOrderStatusDTO() {}

	public UpdateOrderStatusDTO(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	public OrderStatus toOrderStatus() {
		return OrderStatus.valueOf(this.status.toUpperCase());
	}
}	
