package com.seg.precaution.exception.controller.privilege;

public class PrivilegeNotFoundException extends RuntimeException{
    
    private static final String MESSAGE = "Privilege not found";

    public PrivilegeNotFoundException() {
        super(MESSAGE);
    }
}
