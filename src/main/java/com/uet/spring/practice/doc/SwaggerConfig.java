package com.uet.spring.practice.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"SpringBoot Practice REST API",
				"SpringBoot Practice",
				"1.0",
				"http://localhost:8080",
				new Contact("Hung_Pham", "http://localhost:8080", "hungpt58.uet@gmail.com"),
				"License of API", "http://localhost:8080", Collections.emptyList());
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.enable(true)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.uet.spring.practice.controller"))
			.paths(PathSelectors.any())
			.build();
	}

	@Bean
	public void alertSwaggerLink() {
		System.out.println("=============[ Open link: http://localhost:8080/swagger-ui/index.html ]=====================");
	}

}
