package com.natanribeiro.appvendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natanribeiro.appvendas.domain.entity.OrderItem;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer>{

}
