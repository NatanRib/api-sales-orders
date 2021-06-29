package com.natanribeiro.appvendas.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.natanribeiro.appvendas.domain.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Integer>{
	
	@Query(value="select p from Order p where p.customer.id = :id")
	public List<Order> findByCustomerId(@Param("id") int clienteId);//tambem funciona como Query Method

	public Optional<Order> findByIdAndItemsId(Integer Id, Integer itemId);
	
	public Optional<Order> findByIdAndUserId(Integer Id, Integer userId);
}
