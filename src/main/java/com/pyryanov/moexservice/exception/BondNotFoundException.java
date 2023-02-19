package com.pyryanov.moexservice.exception;

public class BondNotFoundException extends RuntimeException {
    public BondNotFoundException(String msg) {
        super(msg);
    }
}
