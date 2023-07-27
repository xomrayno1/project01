package com.tamnc.service.message;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.tamnc.objects.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {
	private String id;
	private String sagaId;
	private String orderId;
	private String paymentId;
	private String customerId;
	private BigDecimal price;
	private Instant createAt;
	private PaymentStatus paymentStatus;
	private List<String> failureMessage; 
	
}
