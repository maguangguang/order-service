package com.tw.flyhigh.service.impl;

import com.tw.flyhigh.common.exception.BusinessException;
import com.tw.flyhigh.common.exception.NoMoreSeatException;
import com.tw.flyhigh.dto.Order;
import com.tw.flyhigh.dto.ReleaseSeatRequest;
import com.tw.flyhigh.dto.ReserveSeatRequest;
import com.tw.flyhigh.entity.TicketOrderEntity;
import com.tw.flyhigh.integration.client.PriceSeatManagerClient;
import com.tw.flyhigh.repository.TicketOrderEventRepository;
import com.tw.flyhigh.repository.TicketOrderRepository;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

import static com.tw.flyhigh.OrderFixture.getCreateOrderDto;
import static com.tw.flyhigh.OrderFixture.getOrder;
import static com.tw.flyhigh.OrderFixture.getOrderEntity;
import static com.tw.flyhigh.OrderFixture.getOrderEventEntity;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {
    @Spy
    TicketOrderRepository orderRepositorySpy;
    @Spy
    TicketOrderEventRepository orderEventRepositorySpy;
    @Spy
    PriceSeatManagerClient priceSeatManagerClient;
    @Mock
    AmqpMessageServiceImpl amqpMessageServiceImpl;
    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    @Captor
    ArgumentCaptor<ReserveSeatRequest> reserveRequestCaptor;

    @Captor
    ArgumentCaptor<ReleaseSeatRequest> releaseRequestCaptor;

    @Captor
    ArgumentCaptor<TicketOrderEntity> order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create_order_successful_and_reserved_seat() {
        when(priceSeatManagerClient.reserveSeat(any())).thenReturn(true);
        when(orderRepositorySpy.save(any())).thenReturn(any());

        Order result = orderServiceImpl.createOrder(getCreateOrderDto());
        Assertions.assertEquals(getOrder(), result);

        verify(priceSeatManagerClient).reserveSeat(reserveRequestCaptor.capture());
        Assertions.assertEquals("MU2151", reserveRequestCaptor.getValue().getFlight());
        Assertions.assertEquals(1, reserveRequestCaptor.getValue().getNumber());

        verify(orderRepositorySpy).save(order.capture());
        Assertions.assertEquals("张三", order.getValue().getContactName());
    }

    @Test
    void should_throw_exception_when_seat_manager_server_to_reserve_seat_and_no_more_seat() {
        when(priceSeatManagerClient.reserveSeat(any())).thenReturn(false);
        Assertions.assertThrows(NoMoreSeatException.class, () -> orderServiceImpl.createOrder(getCreateOrderDto()));
    }

    @Test
    void should_throw_exception_when_call_seat_manager_server_to_reserve_seat_failed() {
        when(priceSeatManagerClient.reserveSeat(any())).thenThrow(FeignException.FeignServerException.class);
        Assertions.assertThrows(FeignException.FeignServerException.class, () -> orderServiceImpl.createOrder(getCreateOrderDto()));
    }
}
