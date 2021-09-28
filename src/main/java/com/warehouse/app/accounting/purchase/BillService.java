package com.warehouse.app.accounting.purchase;

import com.warehouse.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BillService extends BaseService<Bill,BillRepository> {

    @Autowired
    public BillService(ApplicationContext context, BillRepository repository) {
        super(context, repository);
    }
}
