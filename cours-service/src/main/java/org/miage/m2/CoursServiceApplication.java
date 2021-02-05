package org.miage.m2;

import org.miage.m2.boundary.queue.CoursChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableBinding(CoursChannel.class)
public class CoursServiceApplication {

	public static void main(String[] args) {
		// Server webServer = Server.createWebServer("-web,-webAllowOthers,true,-webPort,8082").start();
		// Server server = Server.createTcpServer("-tcp,-tcpAllowOthers,true,-tcpPort,9092").start();
		SpringApplication.run(CoursServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
		.title("Cours API")
		.version("1.0")
		.description("Documentation Cours API Miage"));
	}
}
