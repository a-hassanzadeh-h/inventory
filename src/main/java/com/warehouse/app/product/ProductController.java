package com.warehouse.app.product;

import com.warehouse.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/product")
public class ProductController extends BaseController<Product,ProductService> {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        super(service);
        this.service = service;
    }
}
