package com.natanribeiro.appvendas.service;

import java.util.List;

import com.natanribeiro.appvendas.resource.dto.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> findAll(CustomerDTO cliente);

	CustomerDTO findById(Integer id);

	CustomerDTO save(CustomerDTO cliente);

	void delete(Integer id);
}
