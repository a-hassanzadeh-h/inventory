package com.warehouse.app.inventory;

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
    @JoinColumn(name = "space_id")
    private Space space;

    @OneToOne
    @JoinColumn(name = "stock_transaction_id")
    private StockTransaction stockTransaction;
}
