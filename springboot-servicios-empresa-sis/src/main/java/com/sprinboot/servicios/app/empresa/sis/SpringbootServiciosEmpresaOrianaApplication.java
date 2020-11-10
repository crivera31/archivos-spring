package com.sprinboot.servicios.app.empresa.sis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.sprinboot.servicios.app.empresa.sis.client.FeignErrorDecoder;

//import com.sprinboot.servicios.app.empresa.sis.shared.FeignErrorDecoder;
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EntityScan({"com.sprinboot.servicios.empresa.commons.entity"})
//@ComponentScan("com.sprinboot.servicios.empresa.commons.components")

public class SpringbootServiciosEmpresaOrianaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosEmpresaOrianaApplication.class, args);
	}
	@Bean
	@LoadBalanced
	public RestTemplate getResTemplate() {
		return new RestTemplate();
	}
	/*@Bean
	public FeignErrorDecoder getFeignErrorDecoder() {
		return new FeignErrorDecoder();
	}*/
}
