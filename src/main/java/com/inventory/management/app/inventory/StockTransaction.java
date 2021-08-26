package com.inventory.management.app.inventory;

import com.inventory.management.app.accounting.OrderLine;
import com.inventory.management.core.base.BaseEntity;
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
