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
import com.natanribeiro.appvendas.resource.dto.product.GetProductDTO;
import com.natanribeiro.appvendas.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;

	@Override
	public List<GetProductDTO> find(Product produto) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Product> example = Example.of(produto, matcher);
		return dao.findAll(example).stream().map(p -> GetProductDTO.fromProduct(p)).collect(Collectors.toList());
	}

	@Override
	public GetProductDTO findById(Integer id) {
		return dao.findById(id).map(p -> GetProductDTO.fromProduct(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Override
	public GetProductDTO save(Product produto) {
		if (produto.getDescription() != null && produto.getPrice() != null) {
			return GetProductDTO.fromProduct(dao.save(produto));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@Override
	public GetProductDTO update(Integer id, Product produto) {
		if (produto.getDescription() != null && produto.getPrice() != null) {
			return GetProductDTO.fromProduct(dao.save(
					dao.findById(id).map(p -> new Product(p.getId(), produto.getDescription(), produto.getPrice()))
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
}
