package com.sprinboot.servicios.app.admin.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan({"com.sprinboot.servicios.app.otros.commons.models.entity","com.sprinboot.servicios.usuario.common.models.entity"})
//@EnableCircuitBreaker
public class SpringbootServiciosAdminBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosAdminBaseApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
