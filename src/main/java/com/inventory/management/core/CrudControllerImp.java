package com.inventory.management.core;

import com.inventory.management.core.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CrudControllerImp<E extends BaseEntity, D extends CrudController<E>> implements CrudController<E> {

    private final D delegate;

    public CrudControllerImp(D delegate) {
        this.delegate = delegate;
    }

    @Override
    @PostMapping
    public ResponseEntity<E> create(@RequestBody E e) {
        return delegate.create(e);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return delegate.findById(id);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<E>> findPage(@PageableDefault Pageable pageable) {
        return delegate.findPage(pageable);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<E>> findAll() {
        return delegate.findAll();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity replaceById(@PathVariable long id, @RequestBody E e) {
        return delegate.replaceById(id, e);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        return delegate.deleteById(id);
    }

}
