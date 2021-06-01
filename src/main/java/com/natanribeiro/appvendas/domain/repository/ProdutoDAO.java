package com.natanribeiro.appvendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natanribeiro.appvendas.domain.entity.Produto;

public interface ProdutoDAO extends JpaRepository<Produto, Integer>{

}
