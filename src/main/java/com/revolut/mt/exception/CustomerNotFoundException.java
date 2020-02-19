package com.revolut.mt.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException() {
        //default constructor
    }

    public CustomerNotFoundException(String errorData) {
        super(errorData);
    }

    public CustomerNotFoundException(String errorData, Throwable cause) {
        super(errorData, cause);
    }

}
