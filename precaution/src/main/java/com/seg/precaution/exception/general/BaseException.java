package com.seg.precaution.exception.general;

public abstract class BaseException extends RuntimeException{

    
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
