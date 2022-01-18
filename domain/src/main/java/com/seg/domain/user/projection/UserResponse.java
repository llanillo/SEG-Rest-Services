package com.seg.domain.user.projection;

import java.util.Set;

import com.seg.domain.commission.projection.CommissionSimple;
import com.seg.domain.enumeration.Career;

public interface UserResponse extends UserInformation {
    
    Long getDni();
    Long getCuil();

    Set<Career> getCareer();
    Set<CommissionSimple> getCommission();
    Set<CommissionSimple> getCommissioned();
}
