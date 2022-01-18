package com.seg.precaution.exception.controller.user;

public class CuilAlreadyExistException extends RuntimeException{

    private static final String MESSAGE = "There is already a user with this CUIL";

    public CuilAlreadyExistException() {
        super(MESSAGE);
    }    
}
