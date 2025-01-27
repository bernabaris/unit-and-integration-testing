package com.github.bernabaris.unitandintegrationtesting.controller;


import com.github.bernabaris.unitandintegrationtesting.dto.OrderDto;
import com.github.bernabaris.unitandintegrationtesting.model.Order;
import com.github.bernabaris.unitandintegrationtesting.service.OrderService;
import com.github.bernabaris.unitandintegrationtesting.util.Converter;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerUnitTest {

    @Mock
    OrderService mockOrderService;

    @InjectMocks
    OrderController underTest;

    @Test
    void create_shouldCreateSuccessfully() {

        //given
        Order order = new Order();
        order.setId(1L);
        order.setBuyer("John Doe");
        order.setPrice(99.9);
        order.setQty(10);

        when(mockOrderService.saveOrder(any())).thenReturn(order);

        //when
        OrderDto request = new OrderDto();
        ResponseEntity<OrderDto> response = underTest.saveOrder(request);
        Order actual = Converter.orderDtoToModel(response.getBody());

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK,response.getStatusCode()),
                () -> assertEquals(order,actual),
                () -> assertEquals(order.getId(),actual.getId()),
                () -> assertEquals(order.getBuyer(),actual.getBuyer()),
                () -> assertEquals(order.getPrice(),actual.getPrice()),
                () -> assertEquals(order.getQty(),actual.getQty())

        );
    }

    @Test
    void create_shouldReturnStatusBadRequest() {
        //given
        when(mockOrderService.saveOrder(any())).thenReturn(null);

        //when
        OrderDto request = new OrderDto();
        ResponseEntity<OrderDto> response = underTest.saveOrder(request);

        //then
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    @Test
    void deleteOrderById_shouldReturnStatusNotFound_whenOrderIdDoesNotExist() {
        //given
        doThrow(new EntityNotFoundException()).
                when(mockOrderService).deleteOrderById(anyLong());

        // when
        ResponseEntity<Void> response = underTest.deleteOrderById(1L);


        //then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
