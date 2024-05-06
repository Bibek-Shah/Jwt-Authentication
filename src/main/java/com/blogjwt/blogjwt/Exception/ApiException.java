package com.blogjwt.blogjwt.Exception;

public class ApiException extends RuntimeException {
    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);

    }
}
