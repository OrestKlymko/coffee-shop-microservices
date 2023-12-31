package org.study.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.orderservice.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
