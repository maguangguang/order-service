package com.tw.flyhigh.integration.mq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {OrderEventSender.class})
@ExtendWith(SpringExtension.class)
class OrderEventSenderTest {
    @MockBean
    private AmqpTemplate amqpTemplate;

    @Autowired
    private OrderEventSender orderEventSender;

    @Test
    void testSendMessage() throws AmqpException {
        doNothing().when(this.amqpTemplate).convertAndSend(any(), any(), any(), any());
        this.orderEventSender.sendMessage("Msg");
        verify(this.amqpTemplate).convertAndSend(any(), any(), any(), any());
    }
}

