package com.warehouse.app.product;

import com.warehouse.app.accounting.orderLine.OrderLine;
import com.warehouse.app.inventory.stock.Stock;
import com.warehouse.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product extends BaseEntity {

    private String name;

    private String description;

    private String sku;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderLine> orderLine = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Stock> stocks = new ArrayList<>();

    private int volume;


    public void setSku(String sku) {
        this.sku = "PRD-".concat(sku);
    }

}
