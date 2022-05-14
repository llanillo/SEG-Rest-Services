package com.seg.domain.commission.dto;

import com.seg.domain.enumeration.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionSummary {
    
    private Long id;
    private Role role;
}
