package com.warehouse.app.product;

import com.warehouse.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/admin/product")
public class ProductAdminController extends BaseController<Product, ProductService> {
    @Autowired
    public ProductAdminController(ProductService service) {
        super(service);
    }
}
