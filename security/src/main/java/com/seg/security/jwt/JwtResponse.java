package com.seg.security.jwt;

import static com.seg.security.enumeration.Header.BEARER;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seg.domain.user.dto.UserSummary;

import lombok.Value;

@Value
public class JwtResponse {

    String accessToken;
    String refreshToken;
    String TYPE = BEARER.toString();
    
    @JsonProperty("user")
    UserSummary userResponse;
}
