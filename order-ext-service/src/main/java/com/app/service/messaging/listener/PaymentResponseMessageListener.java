package com.app.service.messaging.listener;

import com.app.model.response.PaymentResponse;

public interface PaymentResponseMessageListener {
	
	void paymentCompleted(PaymentResponse paymentResponse);
	
	void paymentCancelled(PaymentResponse paymentResponse);

}
