package com.warehouse.app.product;

import com.warehouse.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product,ProductRepository> {

    @Autowired
    public ProductService(ProductRepository repository) {
        super(repository);
    }
}
