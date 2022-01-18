package com.seg.precaution.advice.controller.user;

import com.seg.precaution.exception.controller.user.UserNotFoundException;
import com.seg.precaution.response.ErrorResponse;
import com.seg.precaution.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundAdvice {
    
    private static final String CODE = "605";

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> userNotFoundHandler(final UserNotFoundException e){
        final Response response = new ErrorResponse(CODE, e.getMessage());
        return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
    }
}
