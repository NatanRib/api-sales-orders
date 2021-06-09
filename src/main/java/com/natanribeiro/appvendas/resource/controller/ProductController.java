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

import com.natanribeiro.appvendas.resource.dto.product.CreateProductDTO;
import com.natanribeiro.appvendas.resource.dto.product.GetProductDTO;
import com.natanribeiro.appvendas.service.ProductService;


@RestController
@RequestMapping("/produtos")
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping
	public List<GetProductDTO> find(CreateProductDTO produto){
		return service.find(produto.toProduct());
	}
	
	@GetMapping("{id}")
	public GetProductDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public GetProductDTO save(@RequestBody @Valid CreateProductDTO produto) {
		return service.save(produto.toProduct());
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PutMapping("{id}")
	public GetProductDTO update(@PathVariable Integer id,@RequestBody CreateProductDTO produto) {
		return service.update(id, produto.toProduct());
	}
}
