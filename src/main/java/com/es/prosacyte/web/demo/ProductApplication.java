package com.es.prosacyte.web.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.es.prosacyte.web.demo.infrastructure.persistence")
@EntityScan("com.es.prosacyte.web.demo.domain.model")
public class ProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
}