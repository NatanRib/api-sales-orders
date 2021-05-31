package com.natanribeiro.appvendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natanribeiro.appvendas.domain.entity.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Integer>{

}
