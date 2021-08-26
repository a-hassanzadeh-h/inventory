package com.inventory.management.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController<E> {

    default ResponseEntity<E> create(E e) {return null;}

    default ResponseEntity<E> findById(long id) {return null;}

    default ResponseEntity<Page<E>> findPage(Pageable pageable) {return null;}

    default ResponseEntity<List<E>> findAll() {return null;}

    default ResponseEntity<E> replaceById(long id, E e){ return null;}

    default ResponseEntity<E> deleteById(long e){return null;}


}
