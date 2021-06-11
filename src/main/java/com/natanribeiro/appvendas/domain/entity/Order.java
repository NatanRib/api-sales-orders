package com.natanribeiro.appvendas.domain.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	@Column(nullable = false)
	private Instant createdAt;
	
	@Column(nullable = false)
	private Double total;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> items = new ArrayList<>();
	
	public Order(){}

	public Order(Integer id, String description, Customer customer) {
		super();
		this.id = id;
		this.description = description;
		this.customer = customer;
		this.total = 0.0;
		this.createdAt = Instant.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Double getTotal() {
		total = sumTotal();
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public List<OrderItem> getItems() {
		return items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", description=" + description + ", createdAt=" + createdAt + ", total=" + total
				+ ", customer=" + customer + ", items=" + items + "]";
	}

	private Double sumTotal(){
		Double sum = 0.0;
		for (OrderItem i: items ) {
			sum += (i.getPrice() * i.getQuantity());
		}
		return sum;
	}
}