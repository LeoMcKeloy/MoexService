package com.pyryanov.moexservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BondParsingException.class})
    public ResponseEntity<ErrorDto> handleNotFound(Exception exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BondNotFoundException.class})
    public ResponseEntity<ErrorDto> handleBondNotFound(Exception exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }
}
