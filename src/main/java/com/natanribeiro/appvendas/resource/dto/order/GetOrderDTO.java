package com.natanribeiro.appvendas.resource.dto.order;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.domain.entity.OrderItem;
import com.natanribeiro.appvendas.resource.dto.orderItem.GetOrderItemDTO;

public class GetOrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String description;
	private Integer customerId;
	private String customerName;
	private Instant createdAt;
	private Double total;
	private List<GetOrderItemDTO> items = new ArrayList<>();
	
	public GetOrderDTO() {}

	public GetOrderDTO(Integer id, String description, Integer customerId, String customerName, Instant createdAt, Double total) {
		super();
		this.id = id;
		this.description = description;
		this.customerId = customerId;
		this.customerName = customerName;
		this.createdAt = createdAt;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Double getTotal() {
		return total;
	}

	public List<GetOrderItemDTO> getItems() {
		return items;
	}
	
	public static GetOrderDTO fromOrder(Order o) {
		GetOrderDTO getOrderDTO = new GetOrderDTO(o.getId(), o.getDescription(), o.getCustomer().getId(), 
				o.getCustomer().getName(), o.getCreatedAt(), o.getTotal());
		for (OrderItem i : o.getItems()) {
			getOrderDTO.getItems().add(GetOrderItemDTO.fromOrderItem(i));
		}
		return getOrderDTO;
	}
}
