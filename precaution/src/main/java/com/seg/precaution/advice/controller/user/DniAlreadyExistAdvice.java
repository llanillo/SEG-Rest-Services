package com.seg.precaution.advice.controller.user;

import com.seg.precaution.exception.controller.user.DniAlreadyExistException;
import com.seg.precaution.response.ErrorResponse;
import com.seg.precaution.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DniAlreadyExistAdvice {
        
    private static final String CODE = "603";

    @ExceptionHandler(DniAlreadyExistException.class)
    public ResponseEntity<Response> dniAlreadyExistHandler(final DniAlreadyExistException e){
        final Response response = new ErrorResponse(CODE, e.getMessage());
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }
}
