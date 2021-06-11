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
import com.natanribeiro.appvendas.domain.entity.OrderItem;
import com.natanribeiro.appvendas.domain.entity.Product;
import com.natanribeiro.appvendas.domain.repository.CustomerDAO;
import com.natanribeiro.appvendas.domain.repository.OrderDAO;
import com.natanribeiro.appvendas.domain.repository.OrderItemDAO;
import com.natanribeiro.appvendas.domain.repository.ProductDAO;
import com.natanribeiro.appvendas.resource.dto.order.GetOrderDTO;
import com.natanribeiro.appvendas.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	OrderItemDAO orderItemDAO;
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	private String orderNotFound = "Order not found!";
	private String productNotFound = "Product not found!";
	private String customerNotFound = "Customer not found!";

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
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, customerNotFound);
		}		
		return GetOrderDTO.fromOrder(orderDAO.save(order));
	}

	@Override
	public GetOrderDTO update(Integer id, Order order) {
		Order o = orderDAO.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if(order.getCustomer() != null) {
			o.setCustomer(customerDAO.findById(order.getCustomer().getId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, customerNotFound)));
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

	@Override
	public GetOrderDTO addItem(Integer id, OrderItem orderItem) {
		Optional<Order> order = orderDAO.findById(id); 
		Optional<Product> product = productDAO.findById(orderItem.getProduct().getId());
		if (order.isPresent()) {
			orderItem.setOrder(order.get());;
		}else {			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, orderNotFound);
		}
		if (product.isPresent()) {
			orderItem.setProduct(product.get());
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, productNotFound);
		}
		orderItemDAO.save(orderItem);
		return GetOrderDTO.fromOrder(orderDAO.findById(id).get());
	}

	@Override
	public GetOrderDTO deleteItem(Integer orderId, Integer itemId) {
		Optional<Order> order = orderDAO.findById(orderId);
		if (order.isPresent()) {
			order = orderDAO.findByIdAndItemsId(orderId, itemId);
			if (order.isPresent() ) {				
				orderItemDAO.deleteByIdAndOrderId(itemId, orderId);
				return GetOrderDTO.fromOrder(order.get());
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found or not present at this order!");
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, orderNotFound);
	}
}
