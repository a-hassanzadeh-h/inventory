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

    private final PartnerService partnerService;

    @Autowired
    public BillAdminController(BillService service, PartnerService partnerService) {
        super(service);
        this.partnerService = partnerService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Bill> create(@RequestBody Bill bill) {
        User user = AuthContext.getUser().orElseThrow();
        Partner partner = partnerService.findByUser(user);
        bill.setPartner(partner);
        return ResponseEntity.ok(this.service.create(bill));
    }
}
