package com.seg.precaution.exception.controller.user;

public class EmailAlreadyExistException extends RuntimeException{

    private static final String MESSAGE = "There is already a user with this email";

    public EmailAlreadyExistException() {
        super(MESSAGE);
    }
}
