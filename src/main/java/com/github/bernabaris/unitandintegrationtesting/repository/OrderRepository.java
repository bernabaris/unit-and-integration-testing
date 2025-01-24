package com.github.bernabaris.unitandintegrationtesting.repository;

import com.github.bernabaris.unitandintegrationtesting.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
