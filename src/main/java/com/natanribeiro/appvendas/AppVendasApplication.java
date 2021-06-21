package com.natanribeiro.appvendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.natanribeiro.appvendas.domain.entity.Customer;
import com.natanribeiro.appvendas.domain.entity.MyUser;
import com.natanribeiro.appvendas.domain.entity.Order;
import com.natanribeiro.appvendas.domain.entity.OrderItem;
import com.natanribeiro.appvendas.domain.entity.Product;
import com.natanribeiro.appvendas.domain.repository.CustomerDAO;
import com.natanribeiro.appvendas.domain.repository.OrderDAO;
import com.natanribeiro.appvendas.domain.repository.OrderItemDAO;
import com.natanribeiro.appvendas.domain.repository.ProductDAO;
import com.natanribeiro.appvendas.utils.TokenService;

@SpringBootApplication
public class AppVendasApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AppVendasApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(@Autowired CustomerDAO clientes, @Autowired ProductDAO produtos,
			@Autowired OrderDAO pedidos, @Autowired OrderItemDAO itens, @Autowired TokenService token
			) {
		return args ->{
			Customer c1 = new Customer(null, "Natan", "7687647754");
			Customer c2 = new Customer(null, "Renan", "9864571525");
			Customer c3 = new Customer(null, "Maria", "7609861233");
			
			Product p1 = new Product(null, "Celular Iphone 11", 4500.00);
			Product p2 = new Product(null, "Celular Galaxy s20", 5500.0);
			Product p3= new Product(null, "macbook pro", 14680.90);
			Product p4 = new Product(null, "Notebook itautec m2", 2300.00);
			Product p5 = new Product(null, "TV Sangsung led 51 pol", 3765.10);
			
			Order pp1 = new Order(null, "Venda de mercadoria", c1);
			Order pp2 = new Order(null, "Venda de mercadoria", c2);
			Order pp3 = new Order(null, "Venda de mercadoria", c3);
			Order pp4 = new Order(null, "Venda de mercadoria", c1);
			
			OrderItem ip1 = new OrderItem(null, pp4, p5, 2);
			OrderItem ip2 = new OrderItem(null, pp2, p1, 1);
			OrderItem ip3 = new OrderItem(null, pp1, p2, 3);
			OrderItem ip4 = new OrderItem(null, pp3, p3, 1);
			OrderItem ip5 = new OrderItem(null, pp4, p4, 4);
			
			clientes.saveAll(Arrays.asList(c1,c2,c3));
			produtos.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
			pedidos.saveAll(Arrays.asList(pp1,pp2,pp3,pp4));
			itens.saveAll(Arrays.asList(ip1,ip2,ip3,ip4,ip5));
			
			MyUser u = new MyUser(28, null, null, null, null);
			
			String tokenGen = token.tokenGenerator(u);
			
			System.out.println(tokenGen);
			System.out.println("valid: " + token.isValidToken(tokenGen));
			System.out.println("userId: " + token.getuserId(tokenGen));
		};
	}
}