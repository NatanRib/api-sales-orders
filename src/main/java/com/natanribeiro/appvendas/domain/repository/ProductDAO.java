package com.natanribeiro.appvendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natanribeiro.appvendas.domain.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

}
