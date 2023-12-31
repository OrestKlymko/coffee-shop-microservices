package org.study.baristaservice.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.study.baristaservice.kafka.KafkaProducer;
import org.study.baristaservice.model.KafkaDto;
import org.study.baristaservice.model.Status;

import java.util.Timer;
import java.util.TimerTask;

@Service
@AllArgsConstructor
public class OrderProcess {
	private final BaristaTimer baristaTimer;
	private final KafkaProducer kafkaProducer;
	@KafkaListener(topics = "status", groupId = "myGroup")
	public void getNewOrder(@Payload Status status, @Header("kafka_receivedMessageKey") Long orderNumber) {
		KafkaDto kafkaDto = new KafkaDto(status, orderNumber);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				KafkaDto changingKafkaDtoStatus = baristaTimer.runWithStatus(kafkaDto);
				if(changingKafkaDtoStatus.getStatus().equals(Status.FINISHED)) {
					kafkaProducer.sendMessage(changingKafkaDtoStatus.getOrderNumber(),changingKafkaDtoStatus.getStatus());
					timer.cancel();
				}
			}
		}, 0, 5000);
	}
}
