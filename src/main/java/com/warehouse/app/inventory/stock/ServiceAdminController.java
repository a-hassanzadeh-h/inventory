package com.warehouse.app.inventory.stock;

import com.warehouse.core.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/stock")
public class ServiceAdminController extends BaseController<Stock, StockService> {


    public ServiceAdminController(StockService service) {
        super(service);
    }
}
