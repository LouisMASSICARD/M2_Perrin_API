package org.miage.m2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.miage.m2.boundary.CoursChannel;
import org.miage.m2.boundary.UtilisateursChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients
@SpringBootApplication
@EnableBinding({CoursChannel.class, UtilisateursChannel.class})
@IntegrationComponentScan
public class BackofficeGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackofficeGatewayApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
		.title("Frontoffice API")
		.version("1.0")
		.description("Documentation Frontoffice API Miage"));
	}
}
