package com.inventory.management.app.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.inventory.management.app.accounting.OrderLine;
import com.inventory.management.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product extends BaseEntity {

    private String name;

    private String description;

    private String sku;

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderLine> orderLine = new ArrayList<>();


    public void setSku(String sku) {
        this.sku = "PRD-".concat(sku);
    }
}
