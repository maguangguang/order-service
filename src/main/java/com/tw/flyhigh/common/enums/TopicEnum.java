package com.tw.flyhigh.common.enums;

import lombok.Getter;

@Getter
public enum TopicEnum {
    TOPIC_ORDER("flight.order.topic", "flight.order", "flight.order.*");

    private String exchange;
    private String name;
    private String routeKey;

    TopicEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
