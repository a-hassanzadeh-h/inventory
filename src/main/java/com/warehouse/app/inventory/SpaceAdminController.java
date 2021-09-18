package com.warehouse.app.inventory;

import com.warehouse.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/space")
public class SpaceAdminController extends BaseController<Space, SpaceService> {

    private final SpaceService service;

    @Autowired
    public SpaceAdminController(SpaceService service) {
        super(service);
        this.service = service;
    }
}
