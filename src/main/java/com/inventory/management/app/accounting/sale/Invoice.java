package com.inventory.management.app.accounting.sale;

import com.inventory.management.app.accounting.OrderLine;
import com.inventory.management.app.partner.Partner;
import com.inventory.management.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Invoice extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    private Date date;

    @OneToMany(mappedBy = "invoice")
    private List<OrderLine> orderLines;
}
