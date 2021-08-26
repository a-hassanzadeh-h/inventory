package com.inventory.management.app.product;

import com.inventory.management.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product,ProductRepository> {

    @Autowired
    public ProductService(ProductRepository repository) {
        super(repository);
    }
}
