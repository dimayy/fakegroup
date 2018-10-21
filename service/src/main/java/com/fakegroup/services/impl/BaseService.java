package com.fakegroup.services.impl;

import com.fakegroup.dto.BaseProjectDto;
import com.fakegroup.entities.BaseProjectEntity;
import com.fakegroup.repository.BaseRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public abstract class BaseService<E extends BaseProjectEntity, D extends BaseProjectDto> {

    private final BaseRepository<E> repository;
    private final MapperFacade mapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    public BaseService(BaseRepository<E> repository,
                       MapperFacade mapper,
                       Class<E> entityClass,
                       Class<D> dtoClass) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Transactional(readOnly = true)
    public D findById(long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional(readOnly = true)
    public Page<D> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toDto);
    }

    @Transactional
    public D create(D user) {
        return Optional.of(user)
                .map(this::toEntity)
                .map(this::save)
                .map(this::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public D update(long id, D user) {
        if (id != user.getId()) {
            throw new RuntimeException();
        }

        if (!repository.existsById(id)) {
            throw new RuntimeException();
        }

        return Optional.of(user)
                .map(this::toEntity)
                .map(this::save)
                .map(this::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public D partialUpdate(long id, D user) {
        if (id != user.getId()) {
            throw new RuntimeException();
        }

        if (!repository.existsById(id)) {
            throw new RuntimeException();
        }

        return Optional.of(user)
                .map(this::toEntity)
                .map(this::save)
                .map(this::toDto)
                .orElseThrow(RuntimeException::new);
    }

    private D toDto(E user) {
        return mapper.map(user, dtoClass);
    }

    private E toEntity(D user) {
        return mapper.map(user, entityClass);
    }

    private E save(E toSave) {
        return Optional.of(toSave)
                .map(repository::save)
                .map(repository::refresh)
                .orElseThrow(RuntimeException::new);
    }
    
}
