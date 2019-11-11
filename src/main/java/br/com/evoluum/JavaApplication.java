package br.com.evoluum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
@EnableCircuitBreaker
@ComponentScan(basePackages = "br.com.evoluum")
public class JavaApplication {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Evoluum Test REST API",
				"Teste de consulta de municipios",
				"API 1.0v",
				"Terms of service",
				new Contact("Milton Jacomini Neto", "www.miltonjneto.com.br", "miltonjacomini@gmail.com"),
				"License of API", "API license URL", Collections.emptyList());
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaApplication.class, args);
	}

}
