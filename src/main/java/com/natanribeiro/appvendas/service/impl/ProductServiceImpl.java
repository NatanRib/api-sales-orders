package com.natanribeiro.appvendas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.natanribeiro.appvendas.service.exception.DatabaseException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;

	private String notFound = "product with id %d not found!";
	
	@Override
	public List<GetProductDTO> findAll(Product produto) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Product> example = Example.of(produto, matcher);
		return dao.findAll(example).stream().map(p -> GetProductDTO.fromProduct(p)).collect(Collectors.toList());
	}

	@Override
	public GetProductDTO findById(Integer id) {
		return dao.findById(id).map(p -> GetProductDTO.fromProduct(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format(notFound, id)));
	}

	@Override
	public GetProductDTO save(Product produto) {
		return GetProductDTO.fromProduct(dao.save(produto));
	}

	@Override
	public void delete(Integer id) {
		try {			
			dao.delete(dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format(notFound, id))));
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMostSpecificCause().getMessage());
		}
	}

	@Override
	public GetProductDTO update(Integer id, Product produto) {
		Product p = dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format(notFound, id)));
		if(produto.getDescription() != null) {
			p.setDescription(produto.getDescription());
		}
		if(produto.getPrice() != null) {
			p.setPrice(produto.getPrice());
		}
		return GetProductDTO.fromProduct(dao.save(p));
	}
}
