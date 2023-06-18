package com.zerobase.reservation.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.zerobase.reservation.global.exception.ErrorCode.INVALID_REQUEST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ArgumentException.class)
    public ErrorResponse argumentException(ArgumentException e) {
        return ErrorResponse.builder()
                .code(BAD_REQUEST.value())
                .status(BAD_REQUEST)
                .errorCode(e.getErrorCode())
                .errorMessage(e.getErrorMessage())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponse bindException(BindException e) {
        return ErrorResponse.builder()
                .code(BAD_REQUEST.value())
                .status(BAD_REQUEST)
                .errorCode(INVALID_REQUEST)
                .errorMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build();
    }
}
