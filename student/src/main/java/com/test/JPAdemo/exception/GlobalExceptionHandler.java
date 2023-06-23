package com.test.JPAdemo.exception;

import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // add exception handling code here

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlePeopleNotFoundException(PeopleNotFoundException exc) {
        log.error("Exception: PeopleNotFoundException - >", exc);
        // create a StudentErrorResponse

        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    // add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {
        log.error("Exception: Exception - >", exc);
        // create a StudentErrorResponse
        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        // if exception is BindException, then get the message from the exception
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

