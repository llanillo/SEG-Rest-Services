package com.seg.domain.user.projection;

import java.util.Set;

import com.seg.domain.enumeration.Status;
import com.seg.domain.user.entity.Privilege;

public interface UserData extends UserResponse{
    
    String getPassword();

    Status getStatus();
    
    Set<Privilege> getPrivilege();
}
