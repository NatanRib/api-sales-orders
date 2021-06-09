package com.natanribeiro.appvendas.resource.dto.product;

import com.natanribeiro.appvendas.domain.entity.Product;

public class GetProductDTO {
	private Integer id;
	private String description;
	private Double price;
	
	public GetProductDTO() {}

	public GetProductDTO(Integer id, String description, Double price) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
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

	public Double getPrice() {
		return price;
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
		GetProductDTO other = (GetProductDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static GetProductDTO fromProduct(Product p) {
		return new GetProductDTO(p.getId(), p.getDescription(), p.getPrice());
	}
}
