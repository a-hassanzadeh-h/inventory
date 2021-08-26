package com.inventory.management.core.base;


import com.inventory.management.core.CrudControllerImp;

public abstract class BaseController<E extends BaseEntity, S extends BaseService<E, ?>> extends CrudControllerImp<E, BaseControllerDelegate<E, S>> {

    private final S service;

    public BaseController(S service) {
        super(new BaseControllerDelegate<>(service));
        this.service = service;
    }
}
