package com.revolut.mt.exception;

public class InvalidAmountException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidAmountException() {
        //default constructor
    }

    public InvalidAmountException(String errorData) {
        super(errorData);
    }

    public InvalidAmountException(String errorData, Throwable cause) {
        super(errorData, cause);
    }

}
