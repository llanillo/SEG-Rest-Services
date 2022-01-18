package com.seg.implementation.user;

import java.util.List;

import com.seg.api.user.PrivilegeService;
import com.seg.domain.enumeration.Position;
import com.seg.domain.user.entity.Privilege;
import com.seg.precaution.exception.controller.privilege.PrivilegeNotFoundException;
import com.seg.repository.user.PrivilegeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService{

    private final PrivilegeRepository privilegeRepository;

    @Override
    public Privilege findById(final Long id) {
        return privilegeRepository.findById(id)
            .orElseThrow(() -> new PrivilegeNotFoundException());
    }

    @Override
    public Privilege save(final Privilege privilege) {
        return privilegeRepository.save(privilege);            
    }

    @Override
    public void delete(final Long id) {
        findById(id);
        privilegeRepository.deleteById(id);       
    }

    @Override
    public List<Privilege> findAll() {
        return privilegeRepository.findAll();
    }

    @Override
    public Privilege findByPosition(final Position position) {
        return privilegeRepository.findByPosition(position)
            .orElseThrow(() -> new PrivilegeNotFoundException());
    }

    @Override
    public long count() {
        return privilegeRepository.count();
    }

    @Override
    public Privilege update(Long id, Privilege privilege) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Privilege> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
