package com.natanribeiro.appvendas.resource.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.natanribeiro.appvendas.resource.dto.customer.CreateCustomerDTO;
import com.natanribeiro.appvendas.resource.dto.customer.GetCustomerDTO;
import com.natanribeiro.appvendas.resource.dto.customer.UpdateCustomerDTO;
import com.natanribeiro.appvendas.service.CustomerService;

import io.swagger.annotations.Api;

@Api("Customers")
@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping
	public List<GetCustomerDTO> Find(CreateCustomerDTO customer){
		return service.findAll(customer.toCustomer());
	}
	
	@GetMapping("/{id}")
	public GetCustomerDTO findById(@PathVariable Integer id){
		return service.findById(id);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GetCustomerDTO save(@RequestBody @Valid CreateCustomerDTO customer){
		return service.save(customer.toCustomer());
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PutMapping("{id}")
	public GetCustomerDTO updateById(@PathVariable Integer id, @RequestBody UpdateCustomerDTO customer){
		return service.update(id, customer.toCustomer());
	}
}
	