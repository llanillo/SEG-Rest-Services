package com.seg.precaution.exception.controller.document;

public class DocumentNotFoundException extends RuntimeException{

    private static final String MESSAGE = "Document not found";

    public DocumentNotFoundException() {
        super(MESSAGE);
    }
}
