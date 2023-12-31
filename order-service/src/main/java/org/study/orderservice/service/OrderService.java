package org.study.orderservice.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.study.orderservice.dto.OrderDto;
import org.study.orderservice.kafka.KafkaProducer;
import org.study.orderservice.model.Order;
import org.study.orderservice.model.Status;
import org.study.orderservice.repository.OrderRepository;

import java.util.List;


@AllArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final KafkaProducer kafkaProducer;

	public Order createOrder(OrderDto orderDto) {
		Order order = OrderDto.toOrderModel(orderDto);
		kafkaProducer.sendMessage(order.getOrderNumber(),order.getStatus());
		return orderRepository.save(order);
	}
	public void cancelOrder(long orderNumber) {
		orderRepository.deleteById(orderNumber);
	}

	@KafkaListener(topics = "completedOrder", groupId = "myGroup")
	public void finishedOrder(@Payload Status status, @Header("kafka_receivedMessageKey") Long orderNumber) {
		orderRepository.deleteById(orderNumber);
		System.out.println("deleted");
	}

	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
}
