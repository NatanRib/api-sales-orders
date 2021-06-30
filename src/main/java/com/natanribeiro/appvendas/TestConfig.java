package com.natanribeiro.appvendas;

import java.net.Inet4Address;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.natanribeiro.appvendas.domain.entity.Customer;
import com.natanribeiro.appvendas.domain.entity.MyUser;
import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.domain.entity.OrderItem;
import com.natanribeiro.appvendas.domain.entity.Product;
import com.natanribeiro.appvendas.domain.entity.enums.UserRole;
import com.natanribeiro.appvendas.domain.repository.CustomerDAO;
import com.natanribeiro.appvendas.domain.repository.OrderDAO;
import com.natanribeiro.appvendas.domain.repository.OrderItemDAO;
import com.natanribeiro.appvendas.domain.repository.ProductDAO;
import com.natanribeiro.appvendas.service.impl.UserDetailsServiceImpl;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired 
	CustomerDAO clientes;
	@Autowired
	ProductDAO produtos;
	@Autowired
	OrderDAO pedidos;
	@Autowired
	OrderItemDAO itens;
	@Autowired
	PasswordEncoder encoder;
	@Autowired 
	UserDetailsServiceImpl userService;
	
	@Override
	public void run(String... args) throws Exception {
		Customer c1 = new Customer(null, "Natan", "7687647754");
		Customer c2 = new Customer(null, "Renan", "9864571525");
		Customer c3 = new Customer(null, "Maria", "7609861233");
		
		Product p1 = new Product(null, "Celular Iphone 11", 4500.00);
		Product p2 = new Product(null, "Celular Galaxy s20", 5500.0);
		Product p3= new Product(null, "macbook pro", 14680.90);
		Product p4 = new Product(null, "Notebook itautec m2", 2300.00);
		Product p5 = new Product(null, "TV Sangsung led 51 pol", 3765.10);
		
		MyUser admin = new MyUser(null, "admin", "admin@email.com", "123456",
				Instant.parse("1998-07-25T00:00:00.00Z"), UserRole.ROLE_ADMIN);
		
		MyUser natan = new MyUser(null, "natan", "natan@email.com", "12345",
				Instant.parse("1998-07-25T00:00:00.00Z"), UserRole.ROLE_USER);
		
		Order pp1 = new Order(null, "Venda de mercadoria", c1, admin);
		Order pp2 = new Order(null, "Venda de mercadoria", c2, admin);
		Order pp3 = new Order(null, "Venda de mercadoria", c3, natan);
		Order pp4 = new Order(null, "Venda de mercadoria", c1, natan);
		
		OrderItem ip1 = new OrderItem(null, pp4, p5, 2);
		OrderItem ip2 = new OrderItem(null, pp2, p1, 1);
		OrderItem ip3 = new OrderItem(null, pp1, p2, 3);
		OrderItem ip4 = new OrderItem(null, pp3, p3, 1);
		OrderItem ip5 = new OrderItem(null, pp4, p4, 4);
		
		clientes.saveAll(Arrays.asList(c1,c2,c3));
		produtos.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		userService.save(admin);
		userService.save(natan);
		pedidos.saveAll(Arrays.asList(pp1,pp2,pp3,pp4));
		itens.saveAll(Arrays.asList(ip1,ip2,ip3,ip4,ip5));
		
		System.out.println("IP: " + String.valueOf(Inet4Address.getLocalHost().getHostAddress()));
	}

}
