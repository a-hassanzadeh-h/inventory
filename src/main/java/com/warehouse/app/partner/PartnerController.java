package com.warehouse.app.partner;

import com.warehouse.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

public class PartnerController extends BaseController<Partner,PartnerService> {

    @Autowired
    public PartnerController(PartnerService service) {
        super(service);
    }
}
