package com.seg.precaution.advice.general;

import java.util.HashMap;
import java.util.Map;

import com.seg.precaution.response.ErrorsResponse;
import com.seg.precaution.response.Response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FailedValidationAdvice extends ResponseEntityExceptionHandler{

    private static final String MESSAGE = "Could not validate the request";
    private static final String CODE = "624";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
            HttpHeaders headers, HttpStatus status, WebRequest request) {                
        final Map<String, String> errors = new HashMap<>();        

        e.getBindingResult().getAllErrors().forEach((error) -> {
            
            final String name = ((FieldError) error).getField();
            final String message = error.getDefaultMessage();
            errors.put(name, message);
        });

        final Response response = new ErrorsResponse(CODE, MESSAGE, errors);
        return new ResponseEntity<Object>(response, headers, HttpStatus.BAD_REQUEST);
    }    
}
