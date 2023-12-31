package com.tamnc.kafka.producer.service;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.support.SendResult;

public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {
	void send(String topicName, K key, V message, CompletableFuture<SendResult<K, V>> callback);

}
