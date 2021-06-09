package com.natanribeiro.appvendas.resource.dto.customer;

import java.io.Serializable;

import com.natanribeiro.appvendas.domain.entity.Customer;

public class UpdateCustomerDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String cpf;
	
	public UpdateCustomerDTO(String name, String cpf) {
		super();
		this.name = name;
		this.cpf = cpf;
	}
	
	public UpdateCustomerDTO() {}
	
	public String getName() {
		return name;
	}
	public String getCpf() {
		return cpf;
	}
	public Customer toCustomer () {
		return new Customer(null, name, cpf);
	}
}

