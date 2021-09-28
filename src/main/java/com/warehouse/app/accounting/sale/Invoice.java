package com.warehouse.app.accounting.sale;

import com.warehouse.app.accounting.orderLine.OrderLine;
import com.warehouse.app.partner.Partner;
import com.warehouse.core.base.BaseEntity;
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
