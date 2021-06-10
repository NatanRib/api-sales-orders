package com.natanribeiro.appvendas.service;

import java.util.List;

import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.resource.dto.order.GetOrderDTO;

public interface OrderService {

	public List<GetOrderDTO> find (Order order);

	public GetOrderDTO findById(Integer id);

	public GetOrderDTO save(Order order);

	public GetOrderDTO update(Integer id, Order order);

	public void delete(Integer id);
}
