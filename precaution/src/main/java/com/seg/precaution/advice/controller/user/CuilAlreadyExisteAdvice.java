package com.seg.precaution.advice.controller.user;

import com.seg.precaution.exception.controller.user.CuilAlreadyExistException;
import com.seg.precaution.response.ErrorResponse;
import com.seg.precaution.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CuilAlreadyExisteAdvice {
    
    private static final String CODE = "602";

    @ExceptionHandler(CuilAlreadyExistException.class)
    public ResponseEntity<Response> cuilAlreadyExistHandler(final CuilAlreadyExistException e){
        final Response response = new ErrorResponse(CODE, e.getMessage());
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }
}
