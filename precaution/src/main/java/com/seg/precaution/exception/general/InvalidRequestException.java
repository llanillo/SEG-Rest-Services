package com.seg.precaution.exception.general;

public class InvalidRequestException extends BaseException{

    private static final String MENSAJE = "Your client has issued a malformed or illegal request";
    
    public InvalidRequestException() {
        super(MENSAJE);
    }
}
