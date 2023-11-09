package com.tw.flyhigh.config;

import com.tw.flyhigh.common.enums.QueueEnum;
import com.tw.flyhigh.common.enums.TopicEnum;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    DirectExchange orderDirect() {
        return ExchangeBuilder
            .directExchange(QueueEnum.QUEUE_AGENT.getExchange())
            .durable(true)
            .build();
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_AGENT.getName());
    }

    @Bean
    Binding orderBinding(DirectExchange orderDirect, Queue orderQueue) {
        return BindingBuilder
            .bind(orderQueue)
            .to(orderDirect)
            .with(QueueEnum.QUEUE_AGENT.getRouteKey());
    }


    @Bean
    public TopicExchange topic() {
        return new TopicExchange(TopicEnum.TOPIC_ORDER.getExchange());
    }

    @Bean
    public Queue topicQueueOrder() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding topicBindingOrder(TopicExchange topic, Queue topicQueueOrder) {
        return BindingBuilder.bind(topicQueueOrder).to(topic).with(TopicEnum.TOPIC_ORDER.getRouteKey());
    }

}
