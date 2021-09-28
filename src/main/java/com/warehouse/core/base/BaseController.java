package com.warehouse.core.base;


import com.warehouse.core.CrudControllerImp;

public abstract class BaseController<E extends BaseEntity, S extends BaseService<E, ?>> extends CrudControllerImp<E, BaseControllerDelegate<E, S>> {

    protected final S service;

    public BaseController(S service) {
        super(new BaseControllerDelegate<>(service));
        this.service = service;
    }
}
