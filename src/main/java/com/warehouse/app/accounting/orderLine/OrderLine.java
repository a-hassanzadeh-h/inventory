package com.warehouse.app.accounting.orderLine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.app.accounting.purchase.Bill;
import com.warehouse.app.accounting.sale.Invoice;
import com.warehouse.app.inventory.Stock;
import com.warehouse.app.product.Product;
import com.warehouse.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class OrderLine extends BaseEntity {

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    @JsonIgnore
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    @JsonIgnore
    private Bill bill;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderLine")
    private List<Stock> stocks = new ArrayList<>();
}
