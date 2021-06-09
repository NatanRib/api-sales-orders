package com.natanribeiro.appvendas.resource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.resource.dto.order.GetOrderDTO;
import com.natanribeiro.appvendas.service.OrderService;

@RestController
@RequestMapping("/orders")
public class PedidoControlller {

	@Autowired
	OrderService service;
	
	@GetMapping
	public List<GetOrderDTO> find(Order order){
		return service.find(order);
	}
}
