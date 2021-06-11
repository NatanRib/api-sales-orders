package com.natanribeiro.appvendas.resource.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.resource.dto.order.CreateOrderDTO;
import com.natanribeiro.appvendas.resource.dto.order.GetOrderDTO;
import com.natanribeiro.appvendas.resource.dto.order.UpdateOrderDTO;
import com.natanribeiro.appvendas.resource.dto.orderItem.CreateOrderItemDTO;
import com.natanribeiro.appvendas.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderControlller {

	@Autowired
	OrderService service;
	
	@GetMapping
	public List<GetOrderDTO> find(Order order){
		return service.find(order);
	}
	
	@GetMapping("/{id}")
	public GetOrderDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GetOrderDTO save(@RequestBody @Valid CreateOrderDTO order) {
		return service.save(order.toOrder());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public GetOrderDTO update(@PathVariable Integer id, @RequestBody UpdateOrderDTO order) {
		return service.update(id, order.toOrder());
	}
	
	@PostMapping("/{id}/add_item")
	public GetOrderDTO addItem(@PathVariable Integer id, @RequestBody CreateOrderItemDTO orderItem) {
		return service.addItem(id, orderItem.toOrderItem());
	}
	
	@DeleteMapping("/{orderId}/delete_item/{itemId}")
	public GetOrderDTO deleteItem(@PathVariable Integer orderId,@PathVariable Integer itemId) {
		return service.deleteItem(orderId, itemId);
	}
}
