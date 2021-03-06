package com.inventory.management.app.accounting.purchase;

import com.inventory.management.app.accounting.OrderLine;
import com.inventory.management.app.partner.Partner;
import com.inventory.management.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Bill extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @OneToMany(mappedBy = "bill")
    private List<OrderLine> orderLines = new ArrayList<>();


}
