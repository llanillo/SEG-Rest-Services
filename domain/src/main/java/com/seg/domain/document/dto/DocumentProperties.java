package com.seg.domain.document.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.seg.domain.commission.dto.CommissionFunction;
import com.seg.domain.enumeration.DocumentType;
import com.seg.domain.user.dto.UserBasic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentProperties implements Serializable{
    
    @Min(1)
    private Long size;

    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    
    @NotNull
    private CommissionFunction commission;            
    @NotNull
    private UserBasic author;   
    @NotNull
    private LocalDateTime date;    
    @NotNull
    private DocumentType documentType;

    @NotEmpty
    private byte[] data;

    private Set<UserBasic> coAuthor;    
}
