package com.seg.precaution.exception.filter;

import com.seg.precaution.exception.general.BaseException;

public class AuthenticationException extends BaseException{

    public AuthenticationException(String message) {
        super(message);
    } 
}