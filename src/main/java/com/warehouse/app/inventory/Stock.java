package com.warehouse.app.inventory;

import com.warehouse.app.accounting.OrderLine;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private OrderLine orderLine;
}
