package com.seg.repository.commission;

import java.util.List;
import java.util.Optional;

import com.seg.domain.commission.entity.Commission;
import com.seg.domain.commission.entity.Function;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long>{
    
    List <Commission> findAll();        

    Optional<Commission> findByFunction(Function function);
}
