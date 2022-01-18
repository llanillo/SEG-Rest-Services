package com.seg.implementation.commission;

import java.util.List;

import com.seg.api.commission.CommissionService;
import com.seg.api.commission.FunctionService;
import com.seg.domain.commission.entity.Commission;
import com.seg.domain.commission.entity.Function;
import com.seg.domain.enumeration.Role;
import com.seg.precaution.exception.controller.commission.CommissionNotFoundException;
import com.seg.repository.commission.CommissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommissionServiceImpl implements CommissionService{

    private final CommissionRepository commissionRepository;
    private final FunctionService functionService;    

    @Override
    public Commission findById(final Long id) {
        return commissionRepository.findById(id)
            .orElseThrow(() -> new CommissionNotFoundException());
    }
    
    @Override
    public Commission findByRole(final Role role) {
        final Function funcion = functionService.findByRole(role);
        return commissionRepository.findByFunction(funcion)
            .orElseThrow(() -> new CommissionNotFoundException());
    }
        
    @Override
    public Commission save(final Commission comision) {        
        commissionRepository.save(comision);
        return comision;
    }

    @Override
    public Commission update(final Long id, final Commission commission) {
        return null;
    }

    @Override
    public List<Commission> findAll() {
        return commissionRepository.findAll();                    
    }
    
    @Override
    public Page<Commission> findAll(final Pageable pageable) {
        return commissionRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return commissionRepository.count();
    }
    
    @Override
    public void delete(final Long id) {
        findById(id);
        commissionRepository.deleteById(id);
    }
}
