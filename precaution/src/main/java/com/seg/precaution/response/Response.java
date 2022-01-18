package com.seg.precaution.response;

import lombok.Data;

@Data
public abstract class Response {
    
    private final String code;    
    private final String details;
}
