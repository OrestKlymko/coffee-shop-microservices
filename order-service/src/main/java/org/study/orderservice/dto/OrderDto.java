package org.study.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.study.orderservice.model.Order;
import org.study.orderservice.model.Status;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private String typeCoffee;
	private String sizeCoffee;
	private String milkType;
	private String pickUp;
	private BigDecimal price;

	public static Order toOrderModel(OrderDto orderDto) {
		return Order.builder()
				.typeCoffee(orderDto.getTypeCoffee())
				.sizeCoffee(orderDto.getSizeCoffee())
				.milkType(orderDto.getMilkType())
				.pickUp(orderDto.getPickUp())
				.price(orderDto.getPrice())
				.status(Status.CREATED)
				.build();
	}
}
