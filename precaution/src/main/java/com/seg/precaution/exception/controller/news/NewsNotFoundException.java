package com.seg.precaution.exception.controller.news;

public class NewsNotFoundException extends RuntimeException{
    
    private static final String MESSAGE = "News not found";

    public NewsNotFoundException() {
        super(MESSAGE);
    }
}
