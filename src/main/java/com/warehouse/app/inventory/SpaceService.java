package com.warehouse.app.inventory;

import com.warehouse.core.base.BaseService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpaceService extends BaseService<Space, SpaceRepository> {


    @Autowired
    public SpaceService(ApplicationContext context, SpaceRepository repository) {
        super(context, repository);
    }


    public String getFullName(Space space) {
        if (space.getParent() != null) {
            return getFullName(space.getParent()) + "/" + space.getName();
        } else {
            return space.getName();
        }
    }

    @Override
    public Space serialize(Space space) {
        Hibernate.initialize(space.getParent());
        return super.serialize(space);
    }
}
