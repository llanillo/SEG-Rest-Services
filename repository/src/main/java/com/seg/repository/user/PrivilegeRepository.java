package com.seg.repository.user;

import java.util.Optional;

import com.seg.domain.enumeration.Position;
import com.seg.domain.user.entity.Privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{
    
    Optional<Privilege> findByPosition(Position position);
}
