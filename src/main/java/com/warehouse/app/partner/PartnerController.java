package com.warehouse.app.partner;

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
@RequestMapping("/admin/partner")
public class PartnerController extends BaseController<Partner,PartnerService> {

    @Autowired
    public PartnerController(PartnerService service) {
        super(service);
    }

    @Override
    @PostMapping
    public ResponseEntity<Partner> create(@RequestBody Partner partner) {
        User user = AuthContext.getUser().orElseThrow();
        partner.setUser(user);
        return super.create(partner);
    }
}
