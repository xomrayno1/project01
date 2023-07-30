package com.tamnc.dataacess.order.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItemEntityId implements Serializable{
	private Long id;
	private OrderEntity orderEntity;
	

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		OrderItemEntityId  that = (OrderItemEntityId) obj;
		return id.equals(that.getId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, orderEntity);
	}

}
