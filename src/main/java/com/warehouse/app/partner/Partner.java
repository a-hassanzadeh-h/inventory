package com.warehouse.app.partner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.app.accounting.purchase.Bill;
import com.warehouse.app.accounting.sale.Invoice;
import com.warehouse.app.user.User;
import com.warehouse.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Partner extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "partner")
    @JsonIgnore
    private List<Bill> bills = new ArrayList<>();

    @OneToMany(mappedBy = "partner")
    @JsonIgnore
    private List<Invoice> invoices = new ArrayList<>();

}
