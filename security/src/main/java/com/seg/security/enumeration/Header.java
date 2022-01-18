package com.seg.security.enumeration;

import org.springframework.http.HttpHeaders;

public enum Header {
    BEARER ("Bearer "),
    AUTHORIZATION (HttpHeaders.AUTHORIZATION);

    private final String header;

    private Header(final String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return header;
    }        
}
