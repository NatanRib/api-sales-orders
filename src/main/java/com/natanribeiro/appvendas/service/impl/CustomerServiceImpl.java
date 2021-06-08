package com.natanribeiro.appvendas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.natanribeiro.appvendas.domain.entity.Customer;
import com.natanribeiro.appvendas.domain.repository.CustomerDAO;
import com.natanribeiro.appvendas.resource.dto.CustomerDTO;
import com.natanribeiro.appvendas.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDAO dao;

	public List<CustomerDTO> findAll(CustomerDTO customer) {
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Customer> example = Example.of(customer.toCustomer(), matcher);
		return dao.findAll(example).stream().map(c -> CustomerDTO.fromCustomer(c)).collect(Collectors.toList());
	}

	public CustomerDTO findById(Integer id) {
		return dao.findById(id).map(c -> CustomerDTO.fromCustomer(c))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CustomerDTO não encontrado"));
	}

	public CustomerDTO save(CustomerDTO cliente) {
		if (cliente.getName() != null && cliente.getCpf() != null) {
			return CustomerDTO.fromCustomer(dao.save(cliente.toCustomer()));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
	
	public void delete(Integer id){
		dao.delete(dao.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}
}
