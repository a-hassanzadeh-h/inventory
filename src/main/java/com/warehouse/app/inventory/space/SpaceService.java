package com.warehouse.app.inventory.space;

import com.warehouse.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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
}
