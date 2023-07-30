package com.tamnc.dataacess.order.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.tamnc.objects.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "orders")
public class OrderEntity {
	@Id
	private UUID id;
	private UUID customerId;
	private UUID trackingId;
	private UUID restaurantId;
	private BigDecimal price;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	private String failureMessage;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private OrderAddressEntity address;
	
	@OneToMany(mappedBy = "orders", cascade = CascadeType .ALL)
	private List<OrderItemEntity> items;
	

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		OrderItemEntityId  that = (OrderItemEntityId) obj;
		return id.equals(that.getId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
}
