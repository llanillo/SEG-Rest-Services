package com.seg.security.jwt;

import static com.seg.security.enumeration.Header.BEARER;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.seg.precaution.exception.filter.ExpiredTokenException;
import com.seg.precaution.exception.filter.JwtException;
import com.seg.precaution.exception.general.ServerException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class JwtSupplier {             
        
    @Value("${jwt.secret}")
    private String SECRET;    
    @Value("${jwt.expiration_ms}")
    private Long EXPIRATION_MS;
    @Value("${jwt.expiration_refresh_ms}")
    private Long EXPIRATION_REFRESH_MS;
    
    private static final String AUTHORIZATION_ERROR = "User is not authorized";    
    private static final String PRIVILEGES = "privileges";
    private static final String ISSUER = "SEG-UNT";             
    private static final String USER = "user";

    public String createToken(final String user, final List<String> roles){
        try{
            return generateToken(user)
                    .withClaim(PRIVILEGES, roles)     
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_MS))                    
                    .sign(userAlgorithm(SECRET));   
        }catch(IllegalArgumentException | JWTCreationException e){
            throw new ServerException();
        }                                  
    }

    public String createRefreshToken(final String user){
        try{
            return generateToken(user)
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_REFRESH_MS))
                    .sign(userAlgorithm(SECRET));
        }catch(IllegalArgumentException | JWTCreationException e){
            throw new ServerException();
        }
    }

    public boolean isBearer(final String authorization){
        return authorization != null && authorization.startsWith(BEARER.toString()) && authorization.split("\\.").length == 3;        
    }

    public String getTokenUser(final String authorization){
        return verify(authorization).getClaim(USER).asString();
    }

    private DecodedJWT verify(final String authorization){
        if (!isBearer(authorization)){
            throw new JwtException(AUTHORIZATION_ERROR);
        }
        try{
            return JWT.require(userAlgorithm(SECRET))
                    .withIssuer(ISSUER).build()
                    .verify(authorization.substring(BEARER.toString().length()));  
        }catch(final TokenExpiredException e){
            throw new ExpiredTokenException();                
        }catch(final JWTVerificationException e){
            throw new JwtException(AUTHORIZATION_ERROR);
        }
    }

    public List<String> roles(final String authorization){
        return Arrays.asList(verify(authorization).getClaim(PRIVILEGES).asArray(String.class));
    }    

    private Builder generateToken(final String user){
        return JWT.create()
                    .withClaim(USER, user)
                    .withIssuer(ISSUER)                                   
                    .withIssuedAt(new Date())
                    .withNotBefore(new Date());
    }

    private Algorithm userAlgorithm(final String secret){
        return Algorithm.HMAC256(secret);
    }
}
