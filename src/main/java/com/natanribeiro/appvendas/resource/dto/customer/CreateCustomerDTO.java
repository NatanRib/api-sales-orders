package com.natanribeiro.appvendas.resource.dto.customer;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.natanribeiro.appvendas.domain.entity.Customer;

public class CreateCustomerDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "The filed name cannot be null or empty")
	private String name;
	
	@NotEmpty(message = "The filed name cannot be null or empty")
	private String cpf;
	
	public CreateCustomerDTO(String name, String cpf) {
		super();
		this.name = name;
		this.cpf = cpf;
	}
	
	public CreateCustomerDTO() {}
	
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
