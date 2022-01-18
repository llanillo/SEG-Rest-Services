package com.seg.precaution.exception.controller.commission;

public class CommissionNotFoundException extends RuntimeException{
 
    private static final String MESSAGE = "Commission not found";

    public CommissionNotFoundException() {
        super(MESSAGE);
    }
}
