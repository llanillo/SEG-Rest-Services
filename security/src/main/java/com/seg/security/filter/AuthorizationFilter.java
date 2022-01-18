package com.seg.security.filter;

import static com.seg.security.enumeration.Header.AUTHORIZATION;
import static com.seg.security.enumeration.Connection.SESSION;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seg.precaution.exception.general.InvalidRequestException;
import com.seg.security.jwt.JwtSupplier;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter{        
    
    private final JwtSupplier jwtSupplier;
    
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {        
        if (!request.getServletPath().startsWith(SESSION.toString())){                     
            final String authorization = request.getHeader(AUTHORIZATION.toString());
            if (jwtSupplier.isBearer(authorization)){
                final String user = jwtSupplier.getTokenUser(authorization);                   
                final Set<GrantedAuthority> authorities = jwtSupplier.roles(authorization)
                                                                        .stream()
                                                                        .map(e -> new SimpleGrantedAuthority(e.toString()))
                                                                        .collect(Collectors.toSet());                    
                final Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);  
            }
            else{
                throw new InvalidRequestException();
            }        
        }        
        filterChain.doFilter(request, response);
    }    
}