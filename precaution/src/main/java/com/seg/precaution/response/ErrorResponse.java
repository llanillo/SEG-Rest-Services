package com.seg.precaution.response;

public final class ErrorResponse extends Response{

    public ErrorResponse(String code, String details) {
        super(code, details);
    }
}
