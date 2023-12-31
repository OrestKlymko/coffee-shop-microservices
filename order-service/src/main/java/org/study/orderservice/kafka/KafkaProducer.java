package org.study.orderservice.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.study.orderservice.model.Status;

@Service
@AllArgsConstructor
public class KafkaProducer {

	private static final String TOPIC = "status";
	private KafkaTemplate<Long, Status> kafkaTemplate;

	public void sendMessage(Long orderNumber, Status status) {
		kafkaTemplate.send(TOPIC, orderNumber, status);

	}
}
