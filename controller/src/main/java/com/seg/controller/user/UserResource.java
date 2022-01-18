package com.seg.controller.user;

import java.util.List;

import javax.validation.constraints.Min;

import com.seg.api.user.UserService;
import com.seg.domain.user.dto.UserSummary;
import com.seg.domain.user.projection.UserInformation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserResource {
    
    private final UserService userService;
        
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<UserSummary> findById(@PathVariable("id") @Min(0) final Long id){        
        return new ResponseEntity<UserSummary>(userService.findById(id), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/all/basic")
    public ResponseEntity<List<UserInformation>> findAllBasicUser (){
        return new ResponseEntity<List<UserInformation>>(userService.findUserInformation(), HttpStatus.OK);
    }
}
