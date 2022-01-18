package com.seg.precaution.advice.general;

import com.seg.precaution.exception.general.InvalidRequestException;
import com.seg.precaution.response.ErrorResponse;
import com.seg.precaution.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidRequestAdvice {
    
    private static final String CODE = "622";
    
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Response> invalidRequestHandler(final InvalidRequestException e){
        final Response response = new ErrorResponse(CODE, e.getMessage());
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }
}
