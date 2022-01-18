package com.seg.precaution.exception.controller.record;

public class RecordNotFoundException extends RuntimeException{
    
    private static final String MEESAGE ="Record not found";

    public RecordNotFoundException() {
        super(MEESAGE);
    }
}
