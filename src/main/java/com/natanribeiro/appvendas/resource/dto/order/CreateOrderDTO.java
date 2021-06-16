package com.natanribeiro.appvendas.resource.dto.order;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.natanribeiro.appvendas.domain.entity.Customer;
import com.natanribeiro.appvendas.domain.entity.Order;

public class CreateOrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Field 'customerId' cannot be null")
	private Integer customerId;
	
	@NotEmpty(message="Field 'description' cannot be null")
	private String description;
	
	public CreateOrderDTO() {}

	public CreateOrderDTO(Integer customerId, String description) {
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
		return new Order(null, description,new Customer(customerId, null, null));
	}
}