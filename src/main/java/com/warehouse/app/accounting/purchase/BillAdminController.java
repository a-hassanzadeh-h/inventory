package com.warehouse.app.accounting.purchase;

import com.warehouse.app.accounting.purchase.model.Bill;
import com.warehouse.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/bill")
public class BillAdminController extends BaseController<Bill, BillService> {

    @Autowired
    public BillAdminController(BillService service) {
        super(service);
    }
}
