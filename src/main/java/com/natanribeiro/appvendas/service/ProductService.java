package com.natanribeiro.appvendas.service;

import java.util.List;

import com.natanribeiro.appvendas.domain.entity.Product;
import com.natanribeiro.appvendas.resource.dto.product.GetProductDTO;

public interface ProductService {

	List<GetProductDTO> find(Product produto);

	GetProductDTO findById(Integer id);

	GetProductDTO save(Product produto);

	void delete(Integer id);

	GetProductDTO update(Integer id, Product produto);
	
}
