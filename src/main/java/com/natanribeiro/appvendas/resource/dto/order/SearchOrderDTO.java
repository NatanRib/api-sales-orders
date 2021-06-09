package com.natanribeiro.appvendas.resource.dto.order;

import java.io.Serializable;

public class SearchOrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer customerId;
	private String customerName;
	
	public SearchOrderDTO() {}

	public SearchOrderDTO(Integer id, Integer customerId, String customerName) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.customerName = customerName;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}
}
