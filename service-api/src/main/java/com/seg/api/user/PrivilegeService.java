package com.seg.api.user;

import com.seg.api.blueprint.service.BasicService;
import com.seg.domain.enumeration.Position;
import com.seg.domain.user.entity.Privilege;

public interface PrivilegeService extends BasicService<Privilege, Privilege>{
    
    Privilege findByPosition(final Position position);
}
