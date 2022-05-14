package com.seg.api.blueprint.service;

public interface ComplexService <E, R, P> extends BasicService<P, R> {
    
    E findEntity(final Long id);

    R convertToResponse(E entity);

    E convertToEntity(P properties);
}
