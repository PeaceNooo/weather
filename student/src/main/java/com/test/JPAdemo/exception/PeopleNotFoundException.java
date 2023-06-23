package com.test.JPAdemo.exception;

public class PeopleNotFoundException extends RuntimeException {

    public PeopleNotFoundException(String message) {
        super(message);
    }

    public PeopleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PeopleNotFoundException(Throwable cause) {
        super(cause);
    }
}

