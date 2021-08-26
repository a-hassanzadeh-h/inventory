package com.inventory.management.app.accounting;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.inventory.management.app.accounting.purchase.Bill;
import com.inventory.management.app.accounting.sale.Invoice;
import com.inventory.management.app.inventory.StockTransaction;
import com.inventory.management.app.product.Product;
import com.inventory.management.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class OrderLine extends BaseEntity {

    private int quantity;

    private double price;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @OneToMany(mappedBy = "orderLine")
    private List<StockTransaction> stockTransactions = new ArrayList<>();

}
