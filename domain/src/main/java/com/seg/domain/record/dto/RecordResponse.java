package com.seg.domain.record.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.seg.domain.document.projection.DocumentSummary;
import com.seg.domain.enumeration.Activity;
import com.seg.domain.enumeration.Role;
import com.seg.domain.user.projection.UserResponse;

import lombok.Data;

@Data
public class RecordResponse implements Serializable{
    
    @NotNull
    private Long id;
        
    @NotNull
    private Role role;        
    @NotNull
    private Activity activity;
    @NotNull
    private LocalDateTime date;
    
    private UserResponse user;    
    private UserResponse admin;
    private DocumentSummary document;    
}
