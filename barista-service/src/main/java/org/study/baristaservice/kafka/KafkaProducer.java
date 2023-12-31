package org.study.baristaservice.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.study.baristaservice.model.Status;

@Service
@AllArgsConstructor
public class KafkaProducer {

	private static final String TOPIC = "completedOrder";
	private KafkaTemplate<Long, Status> kafkaTemplate;

	public void sendMessage(Long orderNumber, Status status) {
		kafkaTemplate.send(TOPIC, orderNumber, status);

	}
}
