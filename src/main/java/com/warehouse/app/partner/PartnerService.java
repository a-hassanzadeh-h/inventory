package com.warehouse.app.partner;

import com.warehouse.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService extends BaseService<Partner,PartnerRepository> {

    @Autowired
    public PartnerService(PartnerRepository repository) {
        super(repository);
    }
}
