package com.warehouse.app.accounting.orderLine;

import com.warehouse.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/order-line")
public class OrderLineAdminController extends BaseController<OrderLine,OrderLineService> {

    @Autowired
    public OrderLineAdminController(OrderLineService service) {
        super(service);
    }
}
