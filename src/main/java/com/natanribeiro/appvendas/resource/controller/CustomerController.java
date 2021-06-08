package com.natanribeiro.appvendas.resource.controller;

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import com.natanribeiro.appvendas.resource.dto.CustomerDTO;
import com.natanribeiro.appvendas.service.CustomerService;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping()
	public List<CustomerDTO> Find(CustomerDTO cliente){
		return service.findAll(cliente);
	}
	
	@GetMapping("/{id}")
	public CustomerDTO findById(@PathVariable Integer id){
		return service.findById(id);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO save(@RequestBody CustomerDTO cliente){
		if (cliente.getName() == null || cliente.getName().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo nome não pode estar vazio");
		} else if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo cpf não pode estar vazio");
		}
		return service.save(cliente);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public CustomerDTO deleteById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateById(@PathVariable Integer id, @RequestBody CustomerDTO cliente){
		service.delete(id);
	}
}
