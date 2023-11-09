package com.tw.flyhigh.service.impl;

import com.tw.flyhigh.integration.mq.OrderEventSender;
import com.tw.flyhigh.service.MessageService;
import org.springframework.stereotype.Component;

@Component
public class AmqpMessageServiceImpl implements MessageService {
    private final OrderEventSender orderEventSender;

    public AmqpMessageServiceImpl(OrderEventSender orderEventSender) {
        this.orderEventSender = orderEventSender;
    }

    @Override
    public void sendMsg(String eventJson) {
        orderEventSender.sendMessage(eventJson);
    }
}
