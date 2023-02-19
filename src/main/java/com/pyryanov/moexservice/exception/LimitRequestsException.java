package com.pyryanov.moexservice.exception;

public class LimitRequestsException extends RuntimeException {
    public LimitRequestsException(String msg) {
        super(msg);
    }
}
