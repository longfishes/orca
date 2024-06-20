package com.longfish.orca;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.longfish.orca.mapper")
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class OrcaSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrcaSpringbootApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
