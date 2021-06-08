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

import com.natanribeiro.appvendas.domain.entity.Product;
import com.natanribeiro.appvendas.domain.repository.ProductDAO;
import com.natanribeiro.appvendas.resource.dto.ProductDTO;
import com.natanribeiro.appvendas.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;

	@Override
	public List<ProductDTO> find(ProductDTO produto) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Product> example = Example.of(produto.toProduct(), matcher);
		return dao.findAll(example).stream().map(p -> ProductDTO.fromProduct(p)).collect(Collectors.toList());
	}

	@Override
	public ProductDTO findById(Integer id) {
		return dao.findById(id).map(p -> ProductDTO.fromProduct(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Override
	public ProductDTO save(ProductDTO produto) {
		if (produto.getDescription() != null && produto.getPrice() != null) {
			return ProductDTO.fromProduct(dao.save(produto.toProduct()));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@Override
	public ProductDTO update(Integer id, ProductDTO produto) {
		if (produto.getDescription() != null && produto.getPrice() != null) {
			return ProductDTO.fromProduct(dao.save(
					dao.findById(id).map(p -> new Product(p.getId(), produto.getDescription(), produto.getPrice()))
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
}
