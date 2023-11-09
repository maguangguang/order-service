package com.tw.flyhigh.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnknownException extends RuntimeException {

    public UnknownException(String message) {
        super(message);
    }
}
