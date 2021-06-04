package com.natanribeiro.appvendas.resource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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

import com.natanribeiro.appvendas.domain.entity.Cliente;
import com.natanribeiro.appvendas.domain.repository.ClienteDAO;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteDAO dao;
	
	@GetMapping()
	public List<Cliente> Find(Cliente cliente){
		
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Cliente> example = Example.of(cliente, matcher);
		
		return dao.findAll(example);
	}
	
	@GetMapping("/{id}")
	public Cliente findById(@PathVariable Integer id){
		return dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody Cliente cliente){
		if (cliente.getNome() == null || cliente.getNome().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo nome não pode estar vazio");
		} else if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo cpf não pode estar vazio");
		}
		return dao.save(cliente);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Cliente deleteById(@PathVariable Integer id) {
		return dao.findById(id).map( cliente -> {
			dao.deleteById(cliente.getId());
			return cliente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@PutMapping("{id}")
	public Cliente updateById(@PathVariable Integer id, @RequestBody Cliente cliente){
		return dao.findById(id)
			.map(c -> {
				c.setNome(cliente.getNome());//preciso mapear as database exception
				return dao.save(c);
			})
			.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
}
