package com.bsoftgroup.springcloudmsbconsulta;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//Indicate route of other project queries
@EnableFeignClients("com.bsoftgroup.springcloudmsbconsulta.interfaces")
@EnableDiscoveryClient
public class SpringCloudMsbConsultaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMsbConsultaApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

//	@Bean
//	//Habilitar el CORS para poder utilizar utilizar JWT
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedMethods("POST", "GET", "PUT", "DELETE", "HEAD")
//						.allowedOrigins("*")
//						.allowCredentials(false);
//			}
//		};
//	}
}
