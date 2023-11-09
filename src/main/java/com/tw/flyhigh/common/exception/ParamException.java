package com.tw.flyhigh.common.exception;

import lombok.Getter;

@Getter
public class ParamException extends RuntimeException {

    private ErrorCode errorCode;

    public ParamException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
