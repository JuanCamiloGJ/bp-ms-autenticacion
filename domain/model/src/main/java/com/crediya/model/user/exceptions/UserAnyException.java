package com.crediya.model.user.exceptions;

public class UserAnyException extends RuntimeException {
    public UserAnyException() {
        super();
    }

    public UserAnyException(Throwable cause) {
        super(cause);
    }

    public UserAnyException(String message) {
        super(message);
    }

    public UserAnyException(String message, Throwable cause) {
        super(message, cause);
    }

    protected UserAnyException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
