package org.study.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "orders")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderNumber;
	private String typeCoffee;
	private String sizeCoffee;
	private String milkType;
	private String pickUp;
	private BigDecimal price;
	@Enumerated(EnumType.STRING)
	private Status status;
}


