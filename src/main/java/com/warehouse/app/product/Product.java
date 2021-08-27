package com.warehouse.app.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.app.accounting.OrderLine;
import com.warehouse.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product extends BaseEntity {

    private String name;

    private String description;

    private String sku;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private List<OrderLine> orderLine = new ArrayList<>();


    public void setSku(String sku) {
        this.sku = "PRD-".concat(sku);
    }

    public void addOrderLine(OrderLine orderLine) {
        orderLine.setProduct(this);
        this.orderLine.add(orderLine);
    }

    public void removeOrderLine(OrderLine orderLine) {
        orderLine.setProduct(null);
        this.orderLine.remove(orderLine);
    }
}
