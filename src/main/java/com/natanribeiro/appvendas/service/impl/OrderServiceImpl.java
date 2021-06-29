package com.natanribeiro.appvendas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.natanribeiro.appvendas.domain.entity.Customer;
import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.domain.entity.OrderItem;
import com.natanribeiro.appvendas.domain.entity.Product;
import com.natanribeiro.appvendas.domain.repository.CustomerDAO;
import com.natanribeiro.appvendas.domain.repository.OrderDAO;
import com.natanribeiro.appvendas.domain.repository.OrderItemDAO;
import com.natanribeiro.appvendas.domain.repository.ProductDAO;
import com.natanribeiro.appvendas.resource.dto.order.GetOrderDTO;
import com.natanribeiro.appvendas.resource.dto.order.UpdateOrderStatusDTO;
import com.natanribeiro.appvendas.service.OrderService;
import com.natanribeiro.appvendas.service.exception.BadRequestException;
import com.natanribeiro.appvendas.service.exception.RecordNotFoundException;

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
	
	private String orderNotFound = "Order with id %d not found!";
	private String productNotFound = "Product with id %d not found!";
	private String customerNotFound = "Customer with id %d not found!";

	
	@Override
	public List<GetOrderDTO> findAll(Example<Order> example) {
		return orderDAO.findAll(example).stream()
				.map(o -> GetOrderDTO.fromOrder(o)).collect(Collectors.toList());
	}

	@Override
	public GetOrderDTO findById(Integer id) {
		return GetOrderDTO.fromOrder(orderDAO.findById(id).orElseThrow(
				() -> new RecordNotFoundException(
						String.format(orderNotFound, id))));
	}
	
	@Override
	public GetOrderDTO save(Order order) {
		Optional<Customer> c = customerDAO.findById(order.getCustomer().getId());
		if (c.isPresent()) {
			order.setCustomer(c.get());
		}else {
			throw new RecordNotFoundException(String.format(
					customerNotFound, order.getCustomer().getId()));
		}		
		return GetOrderDTO.fromOrder(orderDAO.save(order));
	}

	@Override
	public GetOrderDTO update(Integer id, Order order) {
		Order o = orderDAO.findById(id).orElseThrow(() -> 
			new RecordNotFoundException(String.format(orderNotFound, id)));
		
		o.setCustomer(customerDAO.findById(order.getCustomer().getId())
				.orElseThrow(() -> new RecordNotFoundException(
						String.format(customerNotFound, order.getCustomer().getId()))));
		
		if(order.getDescription() != null) {
			o.setDescription(order.getDescription());
		}
		 return GetOrderDTO.fromOrder(orderDAO.save(o));
	}
	
	public void delete(Integer id) {
		orderDAO.delete(orderDAO.findById(id).
				orElseThrow(() -> new RecordNotFoundException(
						String.format(customerNotFound, id))));
	}

	@Override
	public GetOrderDTO addItem(Integer id, OrderItem orderItem) {
		Optional<Order> order = orderDAO.findById(id); 
		Optional<Product> product = productDAO.findById(orderItem.getProduct().getId());
		if (order.isPresent()) {
			orderItem.setOrder(order.get());;
		}else {			
			throw new RecordNotFoundException(String.format(orderNotFound, id));
		}
		if (product.isPresent()) {
			orderItem.setProduct(product.get());
		}else {
			throw new RecordNotFoundException(String.format(
					productNotFound, orderItem.getProduct().getId()));
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
				throw new RecordNotFoundException("Order item not found or not present at order!");
			}
		}
		throw new RecordNotFoundException(String.format(orderNotFound, orderId));
	}

	@Override
	public GetOrderDTO updateStatus(Integer id, UpdateOrderStatusDTO status) {
		Order order = orderDAO.findById(id).orElseThrow(() -> 
			new RecordNotFoundException(String.format(customerNotFound, id)));
		try {
			order.setStatus(status.toOrderStatus());			
			return GetOrderDTO.fromOrder(orderDAO.save(order));
		} catch (IllegalArgumentException e) {
			throw new BadRequestException("Status sent not matching");
		}
	}
}
