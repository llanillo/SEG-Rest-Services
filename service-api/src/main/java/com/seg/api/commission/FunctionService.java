package com.seg.api.commission;

import com.seg.api.blueprint.service.BasicService;
import com.seg.domain.commission.entity.Function;
import com.seg.domain.enumeration.Role;

public interface FunctionService extends BasicService<Function, Function>{
    
    Function findByRole(final Role role);
}
