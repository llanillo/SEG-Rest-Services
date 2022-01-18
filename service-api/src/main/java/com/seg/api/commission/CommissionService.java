package com.seg.api.commission;

import com.seg.api.blueprint.service.BasicService;
import com.seg.domain.commission.entity.Commission;
import com.seg.domain.enumeration.Role;

public interface CommissionService extends BasicService<Commission, Commission>{

    Commission findByRole (final Role role);        
}
