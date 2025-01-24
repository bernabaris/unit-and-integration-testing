package com.github.bernabaris.unitandintegrationtesting.service;

import com.github.bernabaris.unitandintegrationtesting.entity.OrderEntity;
import com.github.bernabaris.unitandintegrationtesting.model.Order;
import com.github.bernabaris.unitandintegrationtesting.repository.OrderRepository;
import com.github.bernabaris.unitandintegrationtesting.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll().stream().map(Converter::orderEntityToModel).collect(Collectors.toList());

    }

    public Order saveOrder(Order order){
        OrderEntity orderEntity = orderRepository.save(Converter.orderModelToEntity(order));
        return Converter.orderEntityToModel(orderRepository.findById(orderEntity.getId()).get());
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).stream().map(Converter::orderEntityToModel).findFirst().orElse(null);
    }

    public Order deleteOrderById(Long id){
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if(orderEntityOptional.isPresent()){
            orderRepository.deleteById(id);
            return Converter.orderEntityToModel(orderEntityOptional.get());
        } else {
            throw new NoSuchElementException("Order not found with id: " + id);
        }

    }





}
