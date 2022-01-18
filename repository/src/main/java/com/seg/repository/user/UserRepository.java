package com.seg.repository.user;

import java.util.List;
import java.util.Optional;

import com.seg.domain.enumeration.Status;
import com.seg.domain.user.entity.User;
import com.seg.domain.user.projection.UserInformation;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
        
    boolean existsByDni (Long dni);
    boolean existsByCuil (Long cuil);
    boolean existsByEmail (String email);
                
    @EntityGraph (value = "Usuario.commissions", type = EntityGraphType.LOAD)
    Optional <User> findById (Long id);

    @EntityGraph (value = "Usuario.priviliges", type = EntityGraphType.LOAD)
    Optional<User> findByDni(@Param("dni") Long dni);
    
    List<UserInformation> findAllByStatus(Status status);
    
    List <User> findAll();
}
