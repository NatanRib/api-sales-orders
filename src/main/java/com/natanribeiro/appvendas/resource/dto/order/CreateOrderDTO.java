package com.natanribeiro.appvendas.resource.dto.order;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.natanribeiro.appvendas.domain.entity.Customer;
import com.natanribeiro.appvendas.domain.entity.MyUser;
import com.natanribeiro.appvendas.domain.entity.Order;

public class CreateOrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Field 'customerId' cannot be null")
	private Integer customerId;
	
	@NotNull(message="Field 'userId' cannot be null")
	private Integer userId;
	
	@NotEmpty(message="Field 'description' cannot be null")
	private String description;
	
	public CreateOrderDTO() {}

	public CreateOrderDTO(Integer customerId, Integer userId, String description) {
		super();
		this.customerId = customerId;
		this.userId = userId;
		this.description = description;
	}

	public Integer getCustomerId() {
		return customerId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public String getDescription() {
		return description;
	}

	public Order toOrder(){
		return new Order(null, description,new Customer(customerId, null, null), new MyUser(userId, null, null, null, null, null));
	}
}
