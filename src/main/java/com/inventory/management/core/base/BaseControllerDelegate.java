package com.inventory.management.core.base;

import com.inventory.management.core.CrudController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseControllerDelegate<E extends BaseEntity, S extends BaseService<E, ?>> implements CrudController<E> {

    private final S service;

    public BaseControllerDelegate(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<E> create(E e) {
        return ResponseEntity.ok(service.create(e));
    }

    @Override
    public ResponseEntity<E> findById(long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<Page<E>> findPage(Pageable pageable) {
        return ResponseEntity.ok(service.findPage(pageable));
    }

    @Override
    public ResponseEntity<List<E>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<E> replaceById(long id, E e) {
        return ResponseEntity.ok(service.replaceById(id, e));
    }

    @Override
    public ResponseEntity<E> deleteById(long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
