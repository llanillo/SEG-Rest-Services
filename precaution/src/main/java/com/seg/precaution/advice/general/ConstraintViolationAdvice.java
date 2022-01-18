package com.seg.precaution.advice.general;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import com.seg.precaution.response.ErrorsResponse;
import com.seg.precaution.response.Response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConstraintViolationAdvice {
    
    private static final String MESSAGE = "Internal server error";    
    private static final String CODE = "620";

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Response> constraintViolationHandler (final ConstraintViolationException e){        
        final Map<String, String> errors = new HashMap<>();        
                
        e.getConstraintViolations().forEach((error) -> {
            final String name = error.getRootBeanClass().getName();
            final String message = error.getPropertyPath() + " - " + error.getMessage();
            errors.put(name, message);
        });
        
        final Response responses = new ErrorsResponse(CODE, MESSAGE, errors);
        return new ResponseEntity<Response>(responses, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
