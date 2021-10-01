package com.warehouse.core.base;

import com.warehouse.core.CrudControllerDelegate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BaseControllerDelegate<E extends BaseEntity, S extends BaseService<E, ?>> implements CrudControllerDelegate<E> {

    private final S service;

    public BaseControllerDelegate(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<E> create(E e) {
        return ResponseEntity.ok(serialize(service.create(e)));
    }

    @Override
    public ResponseEntity<E> findById(long id) {
        return ResponseEntity.ok(serialize(service.findById(id)));
    }

    @Override
    public ResponseEntity<Page<E>> findPage(Pageable pageable) {
        return ResponseEntity.ok(service.findPage(pageable).map(this::serialize));
    }

    @Override
    public ResponseEntity<List<E>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::serialize).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<E> replaceById(long id, E e) {
        return ResponseEntity.ok(serialize(service.replaceById(id, e)));
    }

    @Override
    public ResponseEntity<E> deleteById(long id) {
        return ResponseEntity.ok(serialize(service.deleteById(id)));
    }

    @Override
    public E serialize(E e) {
        return service.serialize(e);
    }

    @Override
    public E deserialize(E e) {
        return service.deserialize(e);
    }
}
