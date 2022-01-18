package com.seg.domain.commission.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommissionFunction implements Serializable{
    
    private Long id;
    private FunctionSummary function;    
}
