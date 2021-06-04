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

import com.natanribeiro.appvendas.domain.entity.Produto;
import com.natanribeiro.appvendas.domain.repository.ProdutoDAO;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoDAO dao;
	
	private String notFound = "Produto não encontrado";
	
	@GetMapping
	public List<Produto> find(Produto produto){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Produto> example = Example.of(produto, matcher);
		return dao.findAll(example); 
	}
	
	@GetMapping("{id}")
	public Produto findById(@PathVariable Integer id) {
		return dao.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, notFound));
	}
	
	@PostMapping
	public Produto save(@RequestBody Produto produto) {
		if (produto.getDescricao() == null || produto.getDescricao().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A propriedade descrição não pode esta vazia");
		} else if (produto.getPreco() == null || produto.getPreco().isNaN()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A propriedade preço não pode esta vazia e/ou deve ser um numero");
		}
		return dao.save(produto);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		dao.findById(id).map(p -> {
			return p;
		}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, notFound));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto update(@PathVariable Integer id,@RequestBody Produto produto) {
		return dao.findById(id).map(p -> {
			p.setDescricao(produto.getDescricao());
			p.setPreco(produto.getPreco());
			return dao.save(p);
		}).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, notFound));
	}
}
