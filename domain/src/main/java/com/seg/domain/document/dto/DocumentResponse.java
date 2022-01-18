package com.seg.domain.document.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.seg.domain.enumeration.DocumentType;
import com.seg.domain.user.dto.UserBasic;

import lombok.Data;

@Data
public class DocumentResponse implements Serializable{
    
    private Long id;
    private Long size;
    
    private String title;
    private String description;
       
    private UserBasic autor;   
    private LocalDateTime date;    
    private DocumentType documentType;
}
