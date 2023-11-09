package com.tw.flyhigh.service;

import com.tw.flyhigh.dto.CreateOrderDto;
import com.tw.flyhigh.dto.FlightDetail;
import com.tw.flyhigh.dto.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(CreateOrderDto createOrderDto);

    List<Order> getOrders(Long userId);

    FlightDetail getFlightDetail(String flight);
}
