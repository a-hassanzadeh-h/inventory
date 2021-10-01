package com.warehouse.app.accounting.purchase;

import com.warehouse.app.partner.Partner;
import com.warehouse.app.partner.PartnerService;
import com.warehouse.app.user.User;
import com.warehouse.auth.base.AuthContext;
import com.warehouse.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
