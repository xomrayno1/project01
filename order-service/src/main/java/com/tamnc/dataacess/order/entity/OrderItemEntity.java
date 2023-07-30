package com.tamnc.dataacess.order.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@IdClass(OrderItemEntityId.class)
@Table(name = "order_items")
public class OrderItemEntity {
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	private OrderEntity order;
	private UUID productId;
	private BigDecimal price;
	private Integer quantity;
	private BigDecimal subTotal;
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		OrderItemEntityId  that = (OrderItemEntityId) obj;
		return id.equals(that.getId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, order);
	}
}
