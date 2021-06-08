package com.natanribeiro.appvendas.service;

import java.util.List;

import com.natanribeiro.appvendas.resource.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> find(ProductDTO produto);

	ProductDTO findById(Integer id);

	ProductDTO save(ProductDTO produto);

	void delete(Integer id);

	ProductDTO update(Integer id, ProductDTO produto);
	
}
