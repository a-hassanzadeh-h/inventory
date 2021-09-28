package com.warehouse.app.accounting;

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

    private int quantity;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Stock> stocks = new ArrayList<>();
}
