package com.revolut.mt.exception;

public class InvalidRequestInputException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidRequestInputException() {
        //default constructor
    }

    public InvalidRequestInputException(String errorData) {
        super(errorData);
    }

    public InvalidRequestInputException(String errorData, Throwable cause) {
        super(errorData, cause);
    }
}
