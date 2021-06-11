package com.natanribeiro.appvendas.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.natanribeiro.appvendas.domain.entity.OrderItem;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer>{

	List<OrderItem> findByOrderId(Integer id);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM tb_order_items oi "
			+ "WHERE oi.id = :orderId and oi.order_id = :orderItemId", nativeQuery = true)
	void deleteByIdAndOrderId(@Param("orderId") Integer orderId, @Param("orderItemId") Integer orderItemId );//apenas para estudo
}
