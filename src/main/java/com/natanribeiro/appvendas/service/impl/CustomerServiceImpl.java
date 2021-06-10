package com.natanribeiro.appvendas.service.impl;

import java.util.List;
import java.util.Optional;
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
import com.natanribeiro.appvendas.resource.dto.customer.GetCustomerDTO;
import com.natanribeiro.appvendas.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDAO dao;

	public List<GetCustomerDTO> findAll(Customer customer) {
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Customer> example = Example.of(customer, matcher);
		return dao.findAll(example).stream().map(c -> GetCustomerDTO.fromCustomer(c)).collect(Collectors.toList());
	}

	public GetCustomerDTO findById(Integer id) {
		return dao.findById(id).map(c -> GetCustomerDTO.fromCustomer(c))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CustomerDTO não encontrado"));
	}

	public GetCustomerDTO save(Customer cliente) {
		return GetCustomerDTO.fromCustomer(dao.save(cliente));
	}
	
	public void delete(Integer id){
		dao.delete(dao.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@Override
	public GetCustomerDTO update(Integer id, Customer customer) {
		Optional<Customer> c = dao.findById(id);
		if (c.isPresent()) {
			c.get().setName(customer.getName());
			c.get().setCpf(customer.getCpf());
			return GetCustomerDTO.fromCustomer(dao.save(c.get()));
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
