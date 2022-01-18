package com.seg.domain.user.dto;

import java.io.Serializable;
import java.util.Set;

import com.seg.domain.commission.dto.CommissionFunction;
import com.seg.domain.enumeration.Career;

import lombok.Data;

@Data
public class UserSummary implements Serializable {
    
    private Long id;        
    private Long dni;
    private Long cuil;
    
    private String apellido;
    private String nombre; 
    private String email;    
        
    private Set<Career> carrera;    
    private Set<CommissionFunction> commission;        
    private Set<CommissionFunction> commissioned;   
}
