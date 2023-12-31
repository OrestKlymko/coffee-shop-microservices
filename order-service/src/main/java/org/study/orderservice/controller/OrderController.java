package org.study.orderservice.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.study.orderservice.dto.OrderDto;
import org.study.orderservice.service.OrderService;


@RestController
@RequestMapping("api/v1/order/")
@AllArgsConstructor
public class OrderController {

	private final OrderService orderService;


	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
		return ResponseEntity.ok(orderService.createOrder(orderDto));
	}

	@PostMapping("/cancel/{id}")
	public ResponseEntity<?> createOrder(@PathVariable long id) {
		orderService.cancelOrder(id);
		return ResponseEntity.ok("Order cancelled");
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllOrders() {

		return ResponseEntity.ok(orderService.getAllOrders());
	}
}
