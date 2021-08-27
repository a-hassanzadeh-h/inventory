package com.warehouse.app.inventory;

import com.warehouse.app.accounting.OrderLine;
import com.warehouse.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
public class StockTransaction extends BaseEntity {

    @OneToOne
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "order_line_id")
    private OrderLine orderLine;
}
