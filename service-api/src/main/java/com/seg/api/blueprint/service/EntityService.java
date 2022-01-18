package com.seg.api.blueprint.service;

public interface EntityService<E> {
    
    E findEntity(final Long id);
}
