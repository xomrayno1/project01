package com.app.kafka.model;

import java.io.Serializable;

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
public class PaymentRequestAvroModel  implements Serializable  {

	private java.lang.String id;
	private java.lang.String sagaId;
	private java.lang.String customerId;
	private java.lang.String orderId;
	private java.math.BigDecimal price;
	private java.time.Instant createdAt;
	private PaymentOrderStatus paymentOrderStatus;
 

}
