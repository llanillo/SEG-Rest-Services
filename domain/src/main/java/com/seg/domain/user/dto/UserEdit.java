package com.seg.domain.user.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.seg.domain.enumeration.Career;

import lombok.Data;

@Data
public class UserEdit implements Serializable{
    
    @Min(0)
    private Long id;
    @Min(0)
    private Long dni;
    @Min(0)
    private Long cuil;

    @NotBlank
    private String lastname;
    @NotBlank
    private String name;    
    @NotBlank
    private String password;    

    @Email   
    @NotBlank
    private String email;    
        
    @NotEmpty
    private Set<Career> career;        
}
