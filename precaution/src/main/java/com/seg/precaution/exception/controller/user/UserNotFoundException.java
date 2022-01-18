package com.seg.precaution.exception.controller.user;

public class UserNotFoundException  extends RuntimeException{

    private static final String MESSAGE = "User not found";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
