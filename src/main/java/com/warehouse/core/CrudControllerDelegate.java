package com.warehouse.core;

import com.warehouse.core.base.BaseEntity;

public interface CrudControllerDelegate<E extends BaseEntity> extends CrudController<E>{

    E serialize(E e);

    E deserialize(E e);

}
