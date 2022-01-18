package com.seg.security.jwt;

import static com.seg.security.enumeration.Header.BEARER;

import lombok.Value;

@Value
public class TokenResponse {
    
    String accessToken;
    String refreshToken;
    String TYPE = BEARER.toString();
}
