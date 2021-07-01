package com.natanribeiro.appvendas.config.documentation;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppVendasDocumentation {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.natanribeiro.appvendas.resource.controller"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.globalRequestParameters(
						Arrays.asList(
								new RequestParameterBuilder()
								.name("Authorization")
								.description("JWT token obteined from /users/auth")
								.required(true)
								.in(ParameterType.HEADER)
								.build()))
				.apiInfo(new ApiInfoBuilder()
						.description("API contruida com o intuido de solidificar "
								+ "meus conhecimentos de Spring boot, assim como implementar "
								+ "novos conceito que estava estudando.")
						.title("API VENDAS")
						.contact(new Contact("Natan Ribeiro", "http://github.com/NatanRib",
								"natanmar98@gmail.com"))
						.version("1.0.0")
						.build());
				
	}
}
