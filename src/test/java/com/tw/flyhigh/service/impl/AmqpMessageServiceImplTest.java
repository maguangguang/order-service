package com.tw.flyhigh.service.impl;

import com.tw.flyhigh.integration.mq.OrderEventSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {AmqpMessageServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AmqpMessageServiceImplTest {
    @Autowired
    private AmqpMessageServiceImpl amqpMessageServiceImpl;

    @MockBean
    private OrderEventSender orderEventSender;

    @Test
    void testSendMsg() {
        doNothing().when(this.orderEventSender).sendMessage(any());
        this.amqpMessageServiceImpl.sendMsg("Event Json");
        verify(this.orderEventSender).sendMessage(any());
    }
}

