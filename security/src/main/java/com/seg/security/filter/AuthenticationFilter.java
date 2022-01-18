package com.seg.security.filter;

import static com.seg.security.enumeration.Header.AUTHORIZATION;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seg.domain.user.dto.UserSummary;
import com.seg.precaution.exception.filter.AuthenticationException;
import com.seg.precaution.exception.general.InvalidRequestException;
import com.seg.precaution.exception.general.ServerException;
import com.seg.security.jwt.JwtResponse;
import com.seg.security.jwt.JwtSupplier;
import com.seg.security.user.CustomUserDetails;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{        

    private static final String GENERAL_ERROR = "We cant't sign into your account";    
    private static final String PENDING_USER = "Your account is pending for approval";
    private static final String BLOCKED_USER = "Your account is blocked";
    private static final String WRONG_CREDENTIALS = "Incorrect DNI or password";
    private static final String AUTHORIZATION_PREFIX = "Basic ";

    private final AuthenticationManager authenticationManager;
    private final JwtSupplier jwtSupplier;

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
            throws AuthenticationException {
        final String autorizacion = request.getHeader(AUTHORIZATION.toString());
        if (autorizacion != null && autorizacion.startsWith(AUTHORIZATION_PREFIX)){
            try{                        
                final String base64Credentials = autorizacion.substring("Basic ".length());
                final byte[] decodedCredentials = Base64.getDecoder().decode(base64Credentials);
                final String credentials = new String(decodedCredentials, StandardCharsets.UTF_8);
                final String[] values = credentials.split(":", 2);
                final String user = values[0];
                final String password = values[1];
                final Authentication token = new UsernamePasswordAuthenticationToken(user, password);            
                return authenticationManager.authenticate(token);                                          
            }catch(final DisabledException e){
                throw new AuthenticationException(BLOCKED_USER);                
            }catch(final LockedException e){
                throw new AuthenticationException(PENDING_USER);
            }catch(final BadCredentialsException e){
                throw new AuthenticationException(WRONG_CREDENTIALS);        
            }catch(final AuthenticationException e){
                throw new AuthenticationException(GENERAL_ERROR);
            }catch(IndexOutOfBoundsException | IllegalArgumentException e){
                throw new InvalidRequestException();
            }
        }
            throw new InvalidRequestException();
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain,
            final Authentication authResult) throws IOException, ServletException {        
        try {
            final CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();  
            final String user = userDetails.getUsername();
            final List<String> authorities = userDetails.verAutoridades();

            final String accessToken = jwtSupplier.createToken(user, authorities);
            final String refreshToken = jwtSupplier.createRefreshToken(user);      
                     
            final UserSummary userResponse = new ModelMapper().map(userDetails.getUserData(), UserSummary.class);
            final JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken, userResponse);
            final String json = new ObjectMapper().writeValueAsString(jwtResponse);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.OK.value());        

            final PrintWriter out = response.getWriter();                   
            out.print(json);
            out.flush();
        }catch(final RuntimeException e){
            throw new ServerException();
        }
    }
}
