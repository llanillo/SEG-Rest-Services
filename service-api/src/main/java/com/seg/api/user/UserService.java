package com.seg.api.user;

import java.util.List;

import com.seg.api.blueprint.service.BasicService;
import com.seg.api.blueprint.service.EntityService;
import com.seg.domain.enumeration.Position;
import com.seg.domain.user.dto.UserProperties;
import com.seg.domain.user.dto.UserSummary;
import com.seg.domain.user.entity.User;
import com.seg.domain.user.projection.UserInformation;

public interface UserService extends EntityService<User>, BasicService<UserProperties, UserSummary>{        

    User findByDni(final Long dni);

    UserSummary addPrivilege(final Long id, final Position position);

    UserSummary removePrivilege(final Long id, final Position position);    

    List<UserInformation> findUserInformation();
        
    boolean existsByDni (final Long dni);

    boolean existsByCuil (final Long cuil);    

    boolean existsByEmail (final String email);    
}
