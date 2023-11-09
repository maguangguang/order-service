package com.tw.flyhigh.integration.mq;

import com.tw.flyhigh.common.enums.TopicEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的生产者
 * Created by macro on 2018/9/14.
 */
@Component
public class OrderEventSender {
    private static Logger LOGGER = LoggerFactory.getLogger(OrderEventSender.class);
    private final AmqpTemplate amqpTemplate;

    public OrderEventSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(String msg) {
        amqpTemplate.convertAndSend(TopicEnum.TOPIC_ORDER.getExchange(), TopicEnum.TOPIC_ORDER.getRouteKey(), msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                return message;
            }
        });
        LOGGER.info("send msg:{}", msg);
    }
}
