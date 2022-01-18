package com.seg.precaution.advice.controller.document;

import com.seg.precaution.exception.controller.document.DocumentNotFoundException;
import com.seg.precaution.response.ErrorResponse;
import com.seg.precaution.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DocumentNotFoundAdvice {
               
    private static final String CODE = "601";

    @ExceptionHandler(DocumentNotFoundException.class)    
    public final ResponseEntity<Response> documentNotFoundHandler(final DocumentNotFoundException e){        
        final Response response = new ErrorResponse(CODE, e.getMessage());
        return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
    }
}
