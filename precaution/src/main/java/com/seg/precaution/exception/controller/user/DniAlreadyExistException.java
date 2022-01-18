package com.seg.precaution.exception.controller.user;

public class DniAlreadyExistException extends RuntimeException{

    private static final String MESSAGE = "There is already a user with this DNI";

    public DniAlreadyExistException() {
        super(MESSAGE);
    }
}
