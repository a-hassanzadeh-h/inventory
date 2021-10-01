package com.warehouse.app.inventory.stock;

import com.warehouse.app.accounting.orderLine.OrderLine;
import com.warehouse.app.inventory.space.Space;
import com.warehouse.app.product.Product;
import com.warehouse.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Stock extends BaseEntity {

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Space space;
}
