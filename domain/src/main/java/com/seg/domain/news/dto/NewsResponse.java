package com.seg.domain.news.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NonNull;

@Data
public class NewsResponse implements Serializable{
        
    @NotNull
    private Long id;    
    @NotBlank
    private String news;    
    @NonNull    
    private LocalDateTime date;    
}
