package com.pyryanov.moexservice.exception;

public class BondParsingException extends RuntimeException {
    public BondParsingException(Exception msg) {
        super(msg);
    }
}
