package com.warehouse.app.inventory.stock;

import com.warehouse.core.base.BaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class StockService extends BaseService<Stock, StockRepository> {


    public StockService(ApplicationContext context, StockRepository repository) {
        super(context, repository);
    }
}
