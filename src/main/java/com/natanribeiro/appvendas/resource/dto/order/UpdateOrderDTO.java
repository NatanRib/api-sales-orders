package com.natanribeiro.appvendas.resource.dto.order;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.natanribeiro.appvendas.domain.entity.Customer;
import com.natanribeiro.appvendas.domain.entity.MyUser;
import com.natanribeiro.appvendas.domain.entity.Order;

public class UpdateOrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Field 'customerId' cannot be null")
	private Integer customerId;
	
	@NotNull(message="Field 'userId' cannot be null")
	private Integer userId;
	
	private String description;
	
	public UpdateOrderDTO() {}

	public UpdateOrderDTO(Integer customerId, String description, Integer userId) {
		super();
		this.customerId = customerId;
		this.userId = userId;
		this.description = description;
	}

	public Integer getCustomerId() {
		return customerId;
	}
	
	public String getDescription() {
		return description;
	}

	public Order toOrder(){
		Customer customer = null;
		MyUser user = null;
		if (customerId != null) customer = new Customer(customerId, null, null);
		if (userId != null) user = new MyUser(userId, null, null, null, null, null);
		return new Order(null, description, customer, user);
	}
}
