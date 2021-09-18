package com.warehouse.app.inventory;

import com.warehouse.app.product.Product;
import com.warehouse.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
public class Stock extends BaseEntity {

    private int quantity;

    @ManyToOne
    @JoinColumn
    private Product product;

    @ManyToOne
    @JoinColumn
    private Space space;
}
