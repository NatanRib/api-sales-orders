package com.natanribeiro.appvendas.resource.dto.order;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.domain.entity.OrderItem;

public class GetOrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer customerId;
	private String customerName;
	private Instant createdAt;
	private Double total;
	private List<GetOrderItemDTO> items = new ArrayList<>();
	
	public GetOrderDTO() {}

	public GetOrderDTO(Integer id, Integer customerId, String customerName, Instant createdAt, Double total) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.customerName = customerName;
		this.createdAt = createdAt;
		this.total = total;
	}

	public Integer getId() {
		return id;
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
		GetOrderDTO getOrderDTO = new GetOrderDTO(o.getId(), o.getCustomer().getId(), 
				o.getCustomer().getName(), o.getCreatedAt(), o.getTotal());
		for (OrderItem i : o.getItems()) {
			getOrderDTO.getItems().add(GetOrderItemDTO.fromOrderItem(i));
		}
		return getOrderDTO;
	}
}
