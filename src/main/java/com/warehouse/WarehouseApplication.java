package com.warehouse;

import com.warehouse.app.accounting.OrderLine;
import com.warehouse.app.accounting.OrderLineRepository;
import com.warehouse.app.accounting.purchase.Bill;
import com.warehouse.app.accounting.purchase.BillRepository;
import com.warehouse.app.accounting.sale.Invoice;
import com.warehouse.app.accounting.sale.InvoiceRepository;
import com.warehouse.app.partner.Partner;
import com.warehouse.app.partner.PartnerRepository;
import com.warehouse.app.product.Product;
import com.warehouse.app.product.ProductRepository;
import com.warehouse.app.user.User;
import com.warehouse.app.user.UserRepository;
import com.warehouse.app.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WarehouseApplication {


	public static void main(String[] args) { SpringApplication.run(WarehouseApplication.class, args); }

	@Bean
	public ApplicationRunner init(UserService userService){
		return args -> userService.populate();
	}
}
