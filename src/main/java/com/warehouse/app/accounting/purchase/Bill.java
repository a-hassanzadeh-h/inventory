package com.warehouse.app.accounting.purchase;

import com.warehouse.app.accounting.orderLine.OrderLine;
import com.warehouse.app.partner.Partner;
import com.warehouse.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Bill extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = new ArrayList<>();
        for (OrderLine line : orderLines) {
            line.setBill(this);
            this.orderLines.add(line);
        }
    }
}
