package com.app.service.messaging.listener;

import org.springframework.stereotype.Service;

import com.app.model.response.PaymentResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener{

	@Override
	public void paymentCompleted(PaymentResponse paymentResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paymentCancelled(PaymentResponse paymentResponse) {
		// TODO Auto-generated method stub
		
	}

}
