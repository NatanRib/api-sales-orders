package com.natanribeiro.appvendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natanribeiro.appvendas.domain.entity.ItemPedido;

public interface ItemPedidoDAO extends JpaRepository<ItemPedido, Integer>{

}
