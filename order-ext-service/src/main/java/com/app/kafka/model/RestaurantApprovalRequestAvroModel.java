package com.app.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantApprovalRequestAvroModel  {
	   
	  
	  private java.lang.String id;
	  private java.lang.String sagaId;
	  private java.lang.String restaurantId;
	  private java.lang.String orderId;
	  private RestaurantOrderStatus restaurantOrderStatus;
	  private java.util.List<Product> products;
	  private java.math.BigDecimal price;
	  private java.time.Instant createdAt;

	   
}
