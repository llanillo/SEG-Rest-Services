package com.seg.implementation.commission;

import java.util.List;

import com.seg.api.commission.FunctionService;
import com.seg.domain.commission.entity.Function;
import com.seg.domain.enumeration.Role;
import com.seg.precaution.exception.controller.commission.FunctionNotFoundException;
import com.seg.repository.commission.FunctionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FunctionServiceImpl implements FunctionService{

    private final FunctionRepository functionRepository;

    @Override
    public Function findByRole(final Role role) {                
        return functionRepository.findByRole(role)
            .orElseThrow(() -> new FunctionNotFoundException());
    }

    @Override
    public List<Function> findAll() {
        return functionRepository.findAll();
    }

    @Override
    public Function findById(final Long id) {
        return functionRepository.findById(id)
            .orElseThrow(() -> new FunctionNotFoundException());
    }

    @Override
    public Function save(final Function function) {
        return functionRepository.save(function);
    }

    @Override
    public void delete(final Long id) {
        findById(id);
        functionRepository.deleteById(id);
    }

    @Override
    public long count() {
        return functionRepository.count();
    }

    @Override
    public Function update(Long id, Function function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Function> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }    
}
