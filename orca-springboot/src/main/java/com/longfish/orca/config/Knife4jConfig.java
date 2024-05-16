package com.longfish.orca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Value("${project.version}")
    private String version;

    @Bean
    public OpenAPI springOpenApi() {
        Contact contact = new Contact()
                .name("longfish")
                .email("longfishes@qq.com");
        Info info = new Info().title("")
                .description("”中软杯A10“智能编辑器赛道 orca接口文档")
                .version(version)
                .contact(contact);
        return new OpenAPI().info(info);
    }
}
