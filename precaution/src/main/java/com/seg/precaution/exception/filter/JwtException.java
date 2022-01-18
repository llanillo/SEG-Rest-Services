package com.seg.precaution.exception.filter;

import com.seg.precaution.exception.general.BaseException;

public class JwtException extends BaseException{

    public JwtException(String message) {
        super(message);
    }
}
