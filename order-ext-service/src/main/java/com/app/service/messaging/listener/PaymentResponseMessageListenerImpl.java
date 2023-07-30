package com.app.service.messaging.listener;

import org.springframework.stereotype.Service;

import com.app.model.response.PaymentResponse;
import com.app.service.impl.OrderPaymentSaga;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener{

	private final OrderPaymentSaga orderPaymentSaga;
	
    public static final String FAILURE_MESSAGE_DELIMITER = ",";

    public PaymentResponseMessageListenerImpl(OrderPaymentSaga orderPaymentSaga) {
        this.orderPaymentSaga = orderPaymentSaga;
    }

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {
        orderPaymentSaga.process(paymentResponse);
        log.info("Order Payment Saga process operation is completed for order id: {}", paymentResponse.getOrderId());
    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {
        orderPaymentSaga.rollback(paymentResponse);
        log.info("Order is roll backed for order id: {} with failure messages: {}",
                paymentResponse.getOrderId(),
                String.join(FAILURE_MESSAGE_DELIMITER, paymentResponse.getFailureMessages()));
    }

}
