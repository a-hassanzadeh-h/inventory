package com.inventory.management.app.partner;

import com.inventory.management.app.accounting.purchase.Bill;
import com.inventory.management.app.accounting.sale.Invoice;
import com.inventory.management.auth.User;
import com.inventory.management.core.base.BaseEntity;
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

    @OneToMany(mappedBy = "partner")
    private List<Bill> bills = new ArrayList<>();

    @OneToMany(mappedBy = "partner")
    private List<Invoice> invoices = new ArrayList<>();

    public Partner(User user){
        this.user = user;
    }
}
