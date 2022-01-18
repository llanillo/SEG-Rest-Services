package com.seg.precaution.advice.general;

import com.seg.precaution.exception.general.ServerException;
import com.seg.precaution.response.ErrorResponse;
import com.seg.precaution.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServerAdvice {
 
    private static final String CODE = "623";
    
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Response> serverHandler(final ServerException e){
        final Response response = new ErrorResponse(CODE, e.getMessage());
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
