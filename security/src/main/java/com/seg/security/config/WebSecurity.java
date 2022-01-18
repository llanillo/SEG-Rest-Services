package com.seg.security.config;

import static com.seg.security.enumeration.Connection.SESSION;

import com.seg.security.enumeration.Connection;
import com.seg.security.filter.AuthenticationFilter;
import com.seg.security.filter.AuthorizationFilter;
import com.seg.security.filter.ExceptionHandlerFilter;
import com.seg.security.jwt.JwtSupplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter{
    
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;       
    
    private final ExceptionHandlerFilter filtroManejoExcepcion;   
    private final JwtSupplier proveedorJwt;
    
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth            
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder);            
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {    
        final AuthenticationFilter filtroAutenticacion = new AuthenticationFilter(authenticationManagerBean(), proveedorJwt);        
        filtroAutenticacion.setFilterProcessesUrl(Connection.loginUrl());

        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()        
            .cors().disable()    
            .csrf().disable()
            .formLogin().disable()
            .authorizeHttpRequests().antMatchers(Connection.loginUrl() + "/**").permitAll().and()
            .authorizeRequests().antMatchers(SESSION.toString() + "/**").permitAll().and()
            .authorizeRequests().anyRequest().authenticated().and()            
            .addFilter(filtroAutenticacion)       
            .addFilterBefore(filtroManejoExcepcion, UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(new AuthorizationFilter(proveedorJwt), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
