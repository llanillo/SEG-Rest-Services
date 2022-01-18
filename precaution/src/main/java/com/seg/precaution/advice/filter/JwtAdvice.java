package com.seg.precaution.advice.filter;

import com.seg.precaution.exception.filter.JwtException;
import com.seg.precaution.response.ErrorResponse;
import com.seg.precaution.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JwtAdvice {
    
    private static final String CODE = "611";

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Response> jwtHandler(final JwtException e){
        final Response response = new ErrorResponse(CODE, e.getMessage());
        return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
    }
}
