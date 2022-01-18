package com.seg.api.blueprint.mapper;

public interface Mapper<E, T> {
    
    T convertToResponse(E entity);
}
