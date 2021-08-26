package com.inventory.management.core.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class BaseService<E extends BaseEntity, R extends BaseRepository<E>> {

    private final R repository;

    public BaseService(R repository) {
        this.repository = repository;
    }

    public E create(E e) {
        return repository.save(e);
    }

    public E findById(long id) {
        return repository.findById(id).orElseThrow();
    }

    public Page<E> findPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public E replaceById(long id, E e) {
        return replace(e, findById(id));
    }

    public E deleteById(long id) {
        return delete(findById(id));
    }

    public E delete(E e) {
        repository.delete(e);
        return e;
    }

    //todo validate target before save
    public E replace(E target, E source) {
        return repository.save(defaults(target, source));
    }

    public E defaults(E target, E source) {
        target.id = source.id;
        target.created = source.created;
        target.updated = source.updated;
        return target;
    }
}
