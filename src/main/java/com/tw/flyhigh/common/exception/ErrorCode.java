package com.tw.flyhigh.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_PARAMETER("10001"),

    CRESERVE_EXCEPTION("10002"),

    CREATE_ORDER_EXCEPTION("10003"),

    GET_ORDER_EXCEPTION("10004"),

    CANCEL_ORDER_EXCEPTION("10005"),

    ORDER_NOT_FOUND("10006"),

    ORDER_RELEASE_SEAT_FAILED("10007"),

    ORDER_ALREADY_CANCELLED("10008");

    private final String value;

    ErrorCode(String value) {
        this.value = value;
    }
}
