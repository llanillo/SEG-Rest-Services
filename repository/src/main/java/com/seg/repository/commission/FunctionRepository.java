package com.seg.repository.commission;

import java.util.Optional;

import com.seg.domain.commission.entity.Function;
import com.seg.domain.enumeration.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long>{
    
    Optional<Function> findByRole(final Role role);
}
