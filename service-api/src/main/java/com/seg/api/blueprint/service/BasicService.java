package com.seg.api.blueprint.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BasicService<E, R> {
    
    R save(final E entity);    

    R update(final Long id, final E entity);    
    
    R findById(final Long id);        
    
    List <R> findAll();    

    Page <R> findAll(final Pageable pageable);   

    long count();
    
    void delete(final Long id);
}
