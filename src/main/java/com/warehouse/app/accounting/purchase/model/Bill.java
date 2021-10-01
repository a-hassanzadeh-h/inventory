package com.warehouse.app.accounting.purchase.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.warehouse.app.accounting.orderLine.OrderLine;
import com.warehouse.app.partner.Partner;
import com.warehouse.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Bill extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner;

    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    @Column
    @Enumerated(EnumType.STRING)
    private BillStatus status;

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines.clear();
        for (OrderLine line : orderLines) {
            line.setBill(this);
            this.orderLines.add(line);
        }
    }
}
