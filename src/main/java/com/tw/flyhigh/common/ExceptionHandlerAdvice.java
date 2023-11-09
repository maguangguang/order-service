package com.tw.flyhigh.common;

import com.tw.flyhigh.common.exception.BusinessException;
import com.tw.flyhigh.common.exception.ErrorCode;
import com.tw.flyhigh.common.exception.NoMoreSeatException;
import com.tw.flyhigh.common.exception.OrderNotFoundException;
import com.tw.flyhigh.common.exception.ReserveException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RestControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {
    MediaType jsonUtf8 = new MediaType("application", "json", StandardCharsets.UTF_8);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionBody> handle(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.badRequest()
            .contentType(jsonUtf8)
            .body(new ExceptionBody(ErrorCode.INVALID_PARAMETER.getValue(),
                Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    @ExceptionHandler(ReserveException.class)
    public ResponseEntity<ExceptionBody> handle(ReserveException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.badRequest()
            .body(new ExceptionBody(ErrorCode.CRESERVE_EXCEPTION.getValue(), exception.getMessage()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionBody> handle(OrderNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoMoreSeatException.class)
    public ResponseEntity<String> handle(NoMoreSeatException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(jsonUtf8).body("机票已售罄");
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handle(BusinessException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(jsonUtf8).body("服务异常，请稍后再试");
    }

    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<ExceptionBody> handle(FeignException.FeignClientException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(jsonUtf8).body(new ExceptionBody(String.valueOf(exception.status()),
            "服务异常，请稍后再试"));
    }

    @ExceptionHandler(FeignException.FeignServerException.class)
    public ResponseEntity<String> handle(FeignException.FeignServerException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(jsonUtf8).body("服务异常，请稍后再试");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(jsonUtf8).body("服务异常，请稍后再试");
    }
}
