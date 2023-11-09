package com.tw.flyhigh.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

    private final String message;

    public OrderNotFoundException(String message) {
        this.message = message;
    }

}
