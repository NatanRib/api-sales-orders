package com.natanribeiro.appvendas.service;

import java.util.List;

import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.resource.dto.order.GetOrderDTO;

public interface OrderService {

	public List<GetOrderDTO> find (Order order);
}
