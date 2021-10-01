package com.warehouse.app.accounting.purchase;

import com.warehouse.app.partner.Partner;
import com.warehouse.app.partner.PartnerService;
import com.warehouse.app.user.User;
import com.warehouse.auth.base.AuthContext;
import com.warehouse.core.base.BaseService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BillService extends BaseService<Bill, BillRepository> {

    private final PartnerService partnerService;

    @Autowired
    public BillService(ApplicationContext context, BillRepository repository, PartnerService partnerService) {
        super(context, repository);
        this.partnerService = partnerService;
    }

    @Override
    public Bill create(Bill bill) {
        User user = AuthContext.getUser().orElseThrow();
        Partner partner = partnerService.findByUser(user);
        bill.setPartner(partner);
        return super.create(bill);
    }

    @Override
    public Bill serialize(Bill bill) {
        Hibernate.initialize(bill.getOrderLines());
        return super.serialize(bill);
    }
}
