package com.warehouse.app.product;

import com.warehouse.auth.AuthController;
import com.warehouse.core.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/product")
public class ProductController extends BaseController<Product, ProductService> {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public ProductController(ProductService service) {
        super(service);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Product>> findAll(@AuthenticationPrincipal User user) {
        logger.info("Principle "+ user);
        return super.findAll();
    }
}
