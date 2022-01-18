package com.seg.precaution.exception.filter;

import com.seg.precaution.exception.general.BaseException;

public class ExpiredTokenException extends BaseException{

    private static final String TOKEN_VENCIDO = "Accesss token has expired";

    public ExpiredTokenException() {
        super(TOKEN_VENCIDO);
    }
}
