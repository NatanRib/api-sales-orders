package com.natanribeiro.appvendas.service;

import java.util.List;

import com.natanribeiro.appvendas.domain.entity.Customer;
import com.natanribeiro.appvendas.resource.dto.customer.GetCustomerDTO;

public interface CustomerService {

	List<GetCustomerDTO> findAll(Customer cliente);

	GetCustomerDTO findById(Integer id);

	GetCustomerDTO save(Customer cliente);

	void delete(Integer id);
	
	GetCustomerDTO update(Integer id, Customer customer);
}
