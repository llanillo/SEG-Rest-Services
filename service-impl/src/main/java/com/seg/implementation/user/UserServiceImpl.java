package com.seg.implementation.user;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.seg.api.commission.CommissionService;
import com.seg.api.user.PrivilegeService;
import com.seg.api.user.UserService;
import com.seg.domain.commission.entity.Commission;
import com.seg.domain.enumeration.Position;
import com.seg.domain.enumeration.Status;
import com.seg.domain.user.dto.UserEdit;
import com.seg.domain.user.dto.UserProperties;
import com.seg.domain.user.dto.UserSummary;
import com.seg.domain.user.entity.Privilege;
import com.seg.domain.user.entity.User;
import com.seg.domain.user.projection.UserInformation;
import com.seg.precaution.exception.controller.user.CuilAlreadyExistException;
import com.seg.precaution.exception.controller.user.DniAlreadyExistException;
import com.seg.precaution.exception.controller.user.EmailAlreadyExistException;
import com.seg.precaution.exception.controller.user.UserNotFoundException;
import com.seg.repository.user.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Qualifier("userModelMapper")
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;
    private final PrivilegeService privilegeService;
    private final CommissionService commissionService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserSummary addPrivilege(final Long id, final Position position) {
        final User user = findEntity(id);
        final Privilege privilege = privilegeService.findByPosition(position);
        user.getPrivilege().add(privilege);     
        return convertToResponse(user);
    }

    @Override
    public UserSummary removePrivilege (final Long id, final Position position){
        final User user = findEntity(id);
        final Privilege privilege = privilegeService.findByPosition(position);
        user.getPrivilege().remove(privilege);
        return convertToResponse(user);        
    }
 
    @Override
    public UserSummary findById(final Long id) {
        return userRepository.findById(id).map(this::convertToResponse)
            .orElseThrow(() -> new UserNotFoundException());        
    }
    
    @Override
    public User findByDni(final Long dni){
        return userRepository.findByDni(dni)
            .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public UserSummary save(final UserProperties userProperties) {
        if (existsByDni(userProperties.getDni())) throw new DniAlreadyExistException();
        if (existsByCuil(userProperties.getCuil())) throw new CuilAlreadyExistException();
        if (existsByEmail(userProperties.getEmail())) throw new EmailAlreadyExistException();        

        final User user = convertToEntity(userProperties);
        final Set<Privilege> privileges = Collections.singleton(Position.USER).stream()
                .map((c) -> privilegeService.findByPosition(c))
                .collect(Collectors.toSet());
        final Set<Commission> commissions = userProperties.getCommission().stream()
                .map((r) -> commissionService.findByRole(r.getFunction().getRole()))
                .collect(Collectors.toSet());                

        user.setStatus(Status.PENDING);
        user.setPassword(bCryptPasswordEncoder.encode(userProperties.getPassword()));
        user.setCommission(commissions);
        user.setPrivilege(privileges);                
        return convertToResponse(userRepository.save(user));
    }

    @Override
    public UserSummary update(final Long id, final UserProperties userProperties) {          
        return userRepository.findById(id).map((user) -> {
            user = convertToEntity(userProperties);
            return convertToResponse(user);
        })
        .orElseGet(() -> {
            final User user = convertToEntity(userProperties);
            return convertToResponse(userRepository.save(user));
        });
    }

    @Override
    public User findEntity(final Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException());  
    }

    @Override
    public List<UserInformation> findUserInformation() {
        return userRepository.findAllByStatus(Status.ACTIVE);            
    }  

    @Override
    public List<UserSummary> findAll() {
        return userRepository.findAll()
                    .stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
    }
    
    @Override
    public Page<UserSummary> findAll(final Pageable pageable) {
        final List<UserSummary> list = userRepository.findAll(pageable)
                                                .getContent()
                                                .stream()
                                                .map(this::convertToResponse)            
                                                .collect(Collectors.toList());
        return new PageImpl<>(list);
    }
    
    @Override
    public boolean existsByDni(final Long dni) {
        return userRepository.existsByDni(dni);
    }

    @Override
    public boolean existsByCuil(final Long cuil) {
        return userRepository.existsByCuil(cuil);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void delete(final Long id) {        
        findById(id);
        userRepository.deleteById(id);
    }

    public UserSummary convertToResponse(final User user){        
        return modelMapper.map(user, UserSummary.class);
    }

    public User convertToEntity(final UserEdit userEdit){
        return modelMapper.map(userEdit, User.class);
    }

    public User convertToEntity(final UserProperties userProperties){        
        return modelMapper.map(userProperties, User.class);
    }

    public User findUser(final Long dni){     
        return userRepository.findById(dni)
            .orElseThrow(() -> new UserNotFoundException());  
    }  
}
