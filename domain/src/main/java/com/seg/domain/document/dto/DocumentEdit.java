package com.seg.domain.document.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.seg.domain.enumeration.DocumentType;
import com.seg.domain.user.dto.UserBasic;

import lombok.Data;

@Data
public class DocumentEdit implements Serializable{
    
    @Min(0)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NotBlank
    private String description;

    @NotNull
    private DocumentType documentType;

    @NotEmpty
    private byte[] data;

    @NotNull
    private UserBasic coAuthor;
}
