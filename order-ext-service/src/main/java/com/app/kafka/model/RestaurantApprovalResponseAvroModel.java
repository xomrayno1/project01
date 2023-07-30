package com.app.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantApprovalResponseAvroModel {
	 private static final long serialVersionUID = -3431989201238018220L;
 
	  private java.lang.String id;
	  private java.lang.String sagaId;
	  private java.lang.String restaurantId;
	  private java.lang.String orderId;
	  private java.time.Instant createdAt;
	  private OrderApprovalStatus orderApprovalStatus;
	  private java.util.List<java.lang.String> failureMessages;
 
}
