package com.seg.precaution.response;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorsResponse extends Response{
    
    private final Map<String, String> errors;    

    public ErrorsResponse(String code, String details, Map<String, String> errors) {
        super(code, details);
        this.errors = errors;
    }
}
