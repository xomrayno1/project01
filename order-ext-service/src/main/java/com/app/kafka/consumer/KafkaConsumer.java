package com.app.kafka.consumer;

import java.util.List;
//<T extends SpecificRecordBase>
public interface KafkaConsumer<T extends Object>{
	
	void receive(List<T> messages, List<Long> keys, List<Integer> partitions, List<Long> offsets);

}
