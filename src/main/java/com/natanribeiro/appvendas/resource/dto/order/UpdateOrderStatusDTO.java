package com.natanribeiro.appvendas.resource.dto.order;

import javax.validation.constraints.NotEmpty;

import com.natanribeiro.appvendas.entity.enums.OrderStatus;

public class UpdateOrderStatusDTO {

	@NotEmpty(message="Field 'status' cannot be null")
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
