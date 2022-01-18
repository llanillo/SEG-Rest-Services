package com.seg.precaution.advice.filter;

import com.seg.precaution.exception.filter.ExpiredTokenException;
import com.seg.precaution.response.ErrorResponse;
import com.seg.precaution.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TokenExpiredAdvice {
    
    private static final String CODE = "612";

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<Response> tokenExpiredHandler(final ExpiredTokenException e){
        final Response response = new ErrorResponse(CODE, e.getMessage());
        return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
    }
}
