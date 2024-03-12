package com.example.infrastructure.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class UserControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserControllerException.class)
    public ResponseEntity<Object> handleUserControllerException(UserControllerException ex, WebRequest request, @Nullable Object body) {
        log.error("UserControllerException: ", ex);

        HttpStatus status = ex.isClientError() ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
        HttpHeaders headers = new HttpHeaders();

        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof UserControllerException) {
            status = ((UserControllerException) ex).isClientError() ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if (body == null) {
            body = "An error occurred: " + ex.getMessage();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
