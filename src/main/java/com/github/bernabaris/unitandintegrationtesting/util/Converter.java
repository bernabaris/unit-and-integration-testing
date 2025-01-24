package com.github.bernabaris.unitandintegrationtesting.util;

import com.github.bernabaris.unitandintegrationtesting.dto.OrderDto;
import com.github.bernabaris.unitandintegrationtesting.entity.OrderEntity;
import com.github.bernabaris.unitandintegrationtesting.model.Order;

public class Converter {

    public static OrderEntity orderModelToEntity(Order order){
        OrderEntity orderEntity = new OrderEntity();
        if(order.getId() != null){
            orderEntity.setId(order.getId());
        }
        orderEntity.setBuyer(order.getBuyer());
        orderEntity.setPrice(order.getPrice());
        orderEntity.setQty(order.getQty());
        return orderEntity;
    }

    public static Order orderEntityToModel(OrderEntity orderEntity){
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setBuyer(orderEntity.getBuyer());
        order.setPrice(orderEntity.getPrice());
        order.setQty(orderEntity.getQty());
        return order;
    }

    public static Order orderDtoToModel(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setBuyer(orderDto.getBuyer());
        order.setPrice(orderDto.getPrice());
        order.setQty(orderDto.getQty());
        return order;
    }

    public static OrderDto orderModelToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setBuyer(order.getBuyer());
        orderDto.setPrice(order.getPrice());
        orderDto.setQty(order.getQty());
        return orderDto;
    }
}
