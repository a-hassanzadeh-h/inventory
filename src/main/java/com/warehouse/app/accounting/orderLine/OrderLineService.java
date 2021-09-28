package com.warehouse.app.accounting.orderLine;

import com.warehouse.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService extends BaseService<OrderLine,OrderLineRepository> {

    @Autowired
    public OrderLineService(ApplicationContext context, OrderLineRepository repository) {
        super(context, repository);
    }
}
