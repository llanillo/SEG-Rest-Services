package com.seg.domain.user.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.seg.domain.commission.dto.CommissionFunction;
import com.seg.domain.enumeration.Career;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProperties{
            
    @Min(1)
    @NotNull(message = "DNI must be 8 or 9 characters")
    private Long dni;    
    @Min(2)
    @NotNull(message = "CUIL must be 8 or 9 characters")
    private Long cuil;
        
    @NotBlank(message = "Lastname must be at least 3 characters")
    private String lastname;
    @NotBlank(message = "Name must be at least 3 characters")
    private String name;    
    @NotBlank(message = "Password must be at least 8 characters")
    private String password;     
    @Email   
    @NotBlank(message = "Email is not valid")
    private String email;    
            
    @NotEmpty(message = "You must select at least 1 career")
    private Set<Career> carrera;        
    @NotEmpty(message = "You must select at least 1 commission")
    private Set<CommissionFunction> commission;
}