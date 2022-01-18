package com.seg.precaution.exception.general;

public class ServerException extends BaseException{

    private static final String MENSAJE = "The server encountered an internal error and was unable to complete your request";    

    public ServerException() {
        super(MENSAJE);
    }

}
