package com.seg.precaution.exception.controller.commission;

public class FunctionNotFoundException extends RuntimeException{
    
    private static final String MESSAGE = "Function not found";

    public FunctionNotFoundException() {
        super(MESSAGE);
    }
}
