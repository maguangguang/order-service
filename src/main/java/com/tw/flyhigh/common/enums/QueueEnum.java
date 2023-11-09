package com.tw.flyhigh.common.enums;

import lombok.Getter;

@Getter
public enum QueueEnum {
    QUEUE_AGENT("agnet.order.queue", "agnet.order", "agnet.order.*");

    private String exchange;
    private String name;
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
