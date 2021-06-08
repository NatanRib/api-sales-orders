package com.natanribeiro.appvendas.resource.dto;

import java.io.Serializable;

import com.natanribeiro.appvendas.domain.entity.Customer;

public class CustomerDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String cpf;
	
	CustomerDTO(){}

	public CustomerDTO(Integer id, String name, String cpf) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDTO other = (CustomerDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Customer toCustomer() {
		return new Customer(null, this.getName(), this.getCpf()); 
	}

	public static CustomerDTO fromCustomer(Customer c) {
		return new CustomerDTO(c.getId(), c.getName(), c.getCpf());
	}
	
	
}
