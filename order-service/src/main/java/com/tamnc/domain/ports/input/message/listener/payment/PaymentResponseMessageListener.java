package com.tamnc.domain.ports.input.message.listener.payment;

import com.tamnc.service.message.PaymentResponse;

public interface PaymentResponseMessageListener {
	
	void paymentCompleted(PaymentResponse paymentResponse);
	
	void paymentCancelled(PaymentResponse paymentResponse);

}
