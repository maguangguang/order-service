package com.tw.flyhigh.controller;

import com.tw.flyhigh.common.exception.OrderNotFoundException;
import com.tw.flyhigh.dto.CreateOrderDto;
import com.tw.flyhigh.dto.Order;
import com.tw.flyhigh.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("机票订单管理接口文档")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Validated @RequestBody CreateOrderDto createOrderDto) {
        Order order = orderServiceImpl.createOrder(createOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping
    public List<Order> getOrders(Long userId) {
        return orderServiceImpl.getOrders(userId);
    }


    @PostMapping("/{orderId}/cancellation")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        try {
            orderServiceImpl.cancelOrder(orderId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
