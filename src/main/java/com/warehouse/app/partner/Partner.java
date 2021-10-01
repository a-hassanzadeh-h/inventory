package com.warehouse.app.partner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.app.accounting.purchase.model.Bill;
import com.warehouse.app.accounting.sale.Invoice;
import com.warehouse.app.user.User;
import com.warehouse.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Partner extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "partner", fetch = FetchType.LAZY)
    private List<Bill> bills = new ArrayList<>();

    @OneToMany(mappedBy = "partner", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Invoice> invoices = new ArrayList<>();

}
