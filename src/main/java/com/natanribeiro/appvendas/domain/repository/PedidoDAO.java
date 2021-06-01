package com.natanribeiro.appvendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.natanribeiro.appvendas.domain.entity.Pedido;

public interface PedidoDAO extends JpaRepository<Pedido, Integer>{
	
	@Query(value="select p from Pedido p where p.cliente.id = :id")
	public List<Pedido> findByClienteId(@Param("id") int clienteId);//tambem funciona como Query Method
}
