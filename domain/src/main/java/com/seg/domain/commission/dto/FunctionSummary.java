package com.seg.domain.commission.dto;

import java.io.Serializable;

import com.seg.domain.enumeration.Role;

import lombok.Data;

@Data
public class FunctionSummary implements Serializable{
    
    private Long id;
    private Role role;
}
