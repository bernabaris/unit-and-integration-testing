package com.github.bernabaris.unitandintegrationtesting.controller;

import com.github.bernabaris.unitandintegrationtesting.dto.OrderDto;
import com.github.bernabaris.unitandintegrationtesting.model.Order;
import com.github.bernabaris.unitandintegrationtesting.service.OrderService;
import com.github.bernabaris.unitandintegrationtesting.util.Converter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orderDtos = orderService.getAllOrders()
                .stream()
                .map(Converter::orderModelToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto){
        Order savedOrder = orderService.saveOrder(Converter.orderDtoToModel(orderDto));
        if(savedOrder == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(Converter.orderModelToDto(savedOrder));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        OrderDto orderDto = Converter.orderModelToDto(order);
        return ResponseEntity.ok(orderDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id){
        try {
            orderService.deleteOrderById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

