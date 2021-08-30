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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WarehouseApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderLineRepository orderLineRepository;

	public static void main(String[] args) { SpringApplication.run(WarehouseApplication.class, args); }

	@PostConstruct
	public void init(){
		User u1 = new User("user1");
		userRepository.save(u1);

		Partner p1 = new Partner(u1);
		partnerRepository.save(p1);


		Bill b1 = new Bill();
		b1.setPartner(p1);
		billRepository.save(b1);


		Invoice i1 = new Invoice();
		i1.setPartner(p1);
		invoiceRepository.save(i1);

		Product pr1 = new Product();
		pr1.setName("pen");
		pr1.setDescription("beautiful pen");
		pr1.setSku("001");
		productRepository.save(pr1);

		OrderLine o1 = new OrderLine();
		o1.setProduct(pr1);
		o1.setPrice(10);
		o1.setQuantity(100);
		o1.setBill(b1);
		orderLineRepository.save(o1);

	}
}
