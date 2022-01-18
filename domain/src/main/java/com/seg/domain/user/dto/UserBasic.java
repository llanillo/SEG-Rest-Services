package com.seg.domain.user.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserBasic implements Serializable{
    
    Long id;   

    String lastname;
    String name;        
    String email;    
}
