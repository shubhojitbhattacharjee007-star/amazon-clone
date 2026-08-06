package com.amazonclone.mapper;

/**
 * Generic contract for mapping between entity and DTO types.
 *
 * @param <E> entity type
 * @param <D> DTO type
 */
public interface EntityMapper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);
}
