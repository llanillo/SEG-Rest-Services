package com.seg.security.user;

import com.seg.api.user.UserService;
import com.seg.domain.user.entity.User;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService{

    private static final String WRONG_CREDENTIALS = "Incorrect DNI or password";

    private final UserService servicioUsuario;
    
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try{            
            final Long dni = Long.parseLong(username);                        
            final User user = servicioUsuario.findByDni(dni);        
            final UserData userData = new ModelMapper().map(user, UserData.class);
            final UserDetails userDetails = new CustomUserDetails(userData);
            return userDetails;            
            
        }catch(final RuntimeException e){
            throw new UsernameNotFoundException(WRONG_CREDENTIALS);
        }
    }
}
