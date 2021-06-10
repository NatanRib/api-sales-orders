package com.natanribeiro.appvendas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.natanribeiro.appvendas.domain.entity.Customer;
import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.domain.repository.CustomerDAO;
import com.natanribeiro.appvendas.domain.repository.OrderDAO;
import com.natanribeiro.appvendas.resource.dto.order.GetOrderDTO;
import com.natanribeiro.appvendas.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	CustomerDAO customerDAO;

	@Override
	public List<GetOrderDTO> find(Order order) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Order> example = Example.of(order, matcher);
		return orderDAO.findAll(example).stream().map(o -> GetOrderDTO.fromOrder(o)).collect(Collectors.toList());
	}

	@Override
	public GetOrderDTO findById(Integer id) {
		return GetOrderDTO.fromOrder(orderDAO.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}
	
	@Override
	public GetOrderDTO save(Order order) {
		Optional<Customer> c = customerDAO.findById(order.getCustomer().getId());
		if (c.isPresent()) {
			order.setCustomer(c.get());
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
		}		
		return GetOrderDTO.fromOrder(orderDAO.save(order));
	}

	@Override
	public GetOrderDTO update(Integer id, Order order) {
		Order o = orderDAO.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if(order.getCustomer() != null) {
			o.setCustomer(customerDAO.findById(order.getCustomer().getId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")));
		}
		if(order.getDescription() != null) {
			o.setDescription(order.getDescription());
		}
		 return GetOrderDTO.fromOrder(orderDAO.save(o));
	}
	
	public void delete(Integer id) {
		orderDAO.delete(orderDAO.findById(id).
				orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}
}
