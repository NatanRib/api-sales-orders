package com.natanribeiro.appvendas;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.natanribeiro.appvendas.domain.entity.Cliente;
import com.natanribeiro.appvendas.domain.entity.ItemPedido;
import com.natanribeiro.appvendas.domain.entity.Pedido;
import com.natanribeiro.appvendas.domain.entity.Produto;
import com.natanribeiro.appvendas.domain.repository.ClienteDAO;
import com.natanribeiro.appvendas.domain.repository.ItemPedidoDAO;
import com.natanribeiro.appvendas.domain.repository.PedidoDAO;
import com.natanribeiro.appvendas.domain.repository.ProdutoDAO;

@SpringBootApplication
public class AppVendasApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AppVendasApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(@Autowired ClienteDAO clientes, @Autowired ProdutoDAO produtos,
			@Autowired PedidoDAO pedidos, @Autowired ItemPedidoDAO itens
			) {
		return args ->{
			Cliente c1 = new Cliente(null, "Natan");
			Cliente c2 = new Cliente(null, "Renan");
			Cliente c3 = new Cliente(null, "Maria");
			
			Produto p1 = new Produto(null, "Celular Iphone 11", 4500.00);
			Produto p2 = new Produto(null, "Celular Galaxy s20", 5500.0);
			Produto p3= new Produto(null, "macbook pro", 14680.90);
			Produto p4 = new Produto(null, "Notebook itautec m2", 2300.00);
			Produto p5 = new Produto(null, "TV Sangsung led 51 pol", 3765.10);
			
			Pedido pp1 = new Pedido(null, c3, Instant.now(), 0.0);
			Pedido pp2 = new Pedido(null, c2, Instant.now(), 0.0);
			Pedido pp3 = new Pedido(null, c3, Instant.now(), 0.0);
			Pedido pp4 = new Pedido(null, c1, Instant.now(), 0.0);
			
			ItemPedido ip1 = new ItemPedido(null, pp4, p5, 2);
			ItemPedido ip2 = new ItemPedido(null, pp2, p1, 1);
			ItemPedido ip3 = new ItemPedido(null, pp1, p2, 3);
			ItemPedido ip4 = new ItemPedido(null, pp3, p3, 1);
			ItemPedido ip5 = new ItemPedido(null, pp4, p4, 4);
			
			clientes.saveAll(Arrays.asList(c1,c2,c3));
			produtos.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
			pedidos.saveAll(Arrays.asList(pp1,pp2,pp3,pp4));
			itens.saveAll(Arrays.asList(ip1,ip2,ip3,ip4,ip5));
		};
	}
}