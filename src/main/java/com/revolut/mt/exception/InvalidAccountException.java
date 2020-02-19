package com.revolut.mt.exception;

public class InvalidAccountException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidAccountException() {
        //default constructor
    }

    public InvalidAccountException(String errorData) {
        super(errorData);
    }

    public InvalidAccountException(String errorData, Throwable cause) {
        super(errorData, cause);
    }

}
