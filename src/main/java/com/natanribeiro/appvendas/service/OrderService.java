package com.natanribeiro.appvendas.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.domain.entity.OrderItem;
import com.natanribeiro.appvendas.resource.dto.order.GetOrderDTO;
import com.natanribeiro.appvendas.resource.dto.order.UpdateOrderStatusDTO;

public interface OrderService {

	List<GetOrderDTO> findAll(Order order, HttpServletRequest request);

	public GetOrderDTO findById(Integer id, HttpServletRequest request);

	public GetOrderDTO save(Order order);

	public GetOrderDTO update(Integer id, Order order);

	public void delete(Integer id);

	public GetOrderDTO addItem(Integer id, OrderItem orderItem);

	public GetOrderDTO deleteItem(Integer orderId, Integer itemId);

	public GetOrderDTO updateStatus(Integer id, UpdateOrderStatusDTO status);
}
