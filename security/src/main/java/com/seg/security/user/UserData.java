package com.seg.security.user;

import java.util.Set;

import com.seg.domain.commission.dto.CommissionFunction;
import com.seg.domain.enumeration.Career;
import com.seg.domain.enumeration.Status;
import com.seg.domain.user.entity.Privilege;

import lombok.Data;

@Data
public class UserData {
    
    private Long id;
    private Long dni;
    private Long cuil;

    private String lastname;
    private String name;
    private String password;
    private String email;

    private Status status;

    private Set<Career> career;
    private Set<Privilege> privilege;
    private Set<CommissionFunction> commission;
    private Set<CommissionFunction> commissioned;
}
