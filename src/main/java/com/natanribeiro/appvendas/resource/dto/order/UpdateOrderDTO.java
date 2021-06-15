package com.natanribeiro.appvendas.resource.dto.order;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.natanribeiro.appvendas.domain.entity.Customer;
import com.natanribeiro.appvendas.domain.entity.Order;

public class UpdateOrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Field 'customerId' cannot be null")
	private Integer customerId;
	
	private String description;
	
	public UpdateOrderDTO() {}

	public UpdateOrderDTO(Integer customerId, String description) {
		super();
		this.customerId = customerId;
		this.description = description;
	}

	public Integer getCustomerId() {
		return customerId;
	}
	
	public String getDescription() {
		return description;
	}

	public Order toOrder(){
		Customer c = null;
		if (customerId != null) {
			c = new Customer(customerId, null, null);
		}
		return new Order(null, description, c);
	}
}
