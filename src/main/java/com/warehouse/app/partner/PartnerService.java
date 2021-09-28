package com.warehouse.app.partner;

import com.warehouse.app.user.User;
import com.warehouse.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PartnerService extends BaseService<Partner, PartnerRepository> {

    @Autowired
    public PartnerService(ApplicationContext context, PartnerRepository repository) {
        super(context, repository);
    }


    public Partner findByUser(User user) {
        return this.repository.findByUser(user).orElseThrow();
    }

}
