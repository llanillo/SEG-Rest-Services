package com.seg.security.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.seg.domain.enumeration.Status;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails{  
    
    private final UserData userData;

    private Collection<? extends GrantedAuthority> authorities;
  
    public CustomUserDetails(final UserData userData) {
        this.userData = userData;

        authorities = userData.getPrivilege().stream()
            .map(c -> new SimpleGrantedAuthority("ROLE_" + c.toString()))
            .collect(Collectors.toSet());      
    }

    public List<String> verAutoridades(){
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {        
        return userData.getPassword();
    }

    @Override
    public String getUsername() {
        return userData.getDni().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userData.getStatus() == Status.PENDING ? false : true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userData.getStatus() == Status.ACTIVE ? true : false;
    }
}
