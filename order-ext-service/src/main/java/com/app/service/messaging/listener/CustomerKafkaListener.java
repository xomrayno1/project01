package com.app.service.messaging.listener;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.app.kafka.consumer.KafkaConsumer;
import com.app.model.dto.CustomerModel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CustomerKafkaListener implements KafkaConsumer<CustomerModel> {
	
    private final CustomerMessageListener customerMessageListener;
  
	
    @Override
    @KafkaListener(id = "${kafka-consumer-config.customer-group-id}", topics = "${order-service.customer-topic-name}")
    public void receive(@Payload List<CustomerModel> messages, 
    		@Header(KafkaHeaders.RECEIVED_KEY) List<Long> keys,
    		@Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions, 
    		@Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of customer create messages received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(customerModel -> customerMessageListener.customerCreated(customerModel));
    }
 


	 
	
}


