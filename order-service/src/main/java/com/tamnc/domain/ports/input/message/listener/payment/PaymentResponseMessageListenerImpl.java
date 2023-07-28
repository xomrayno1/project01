package com.tamnc.domain.ports.input.message.listener.payment;

import org.springframework.stereotype.Service;

import com.tamnc.service.message.PaymentResponse;

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
