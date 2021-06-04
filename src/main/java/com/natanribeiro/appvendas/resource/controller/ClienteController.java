package com.natanribeiro.appvendas.resource.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natanribeiro.appvendas.domain.entity.Cliente;
import com.natanribeiro.appvendas.domain.repository.ClienteDAO;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteDAO dao;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id){
		
		Optional<Cliente> cliente = dao.findById(id);
		
		if (cliente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.get());
	}
	
	@GetMapping()
	public ResponseEntity<List<Cliente>> Find(Cliente cliente){
		
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Cliente> example = Example.of(cliente, matcher);
		
		List<Cliente> clientes = dao.findAll(example);
		return ResponseEntity.ok(clientes);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
		Cliente c = dao.save(cliente);
		return ResponseEntity.ok(c);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Cliente> deleteById(@PathVariable Integer id) {
		
		if(dao.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		dao.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Cliente> updateById(@PathVariable Integer id, @RequestBody Cliente cliente){
		Optional<Cliente> c = dao.findById(id);
		
		if(c.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		c.get().setNome(cliente.getNome());//preciso mapear as database exception
		dao.save(c.get());
		return ResponseEntity.ok(c.get());
	}
}
