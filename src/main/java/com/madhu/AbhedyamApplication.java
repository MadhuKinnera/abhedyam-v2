package com.madhu;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Abhedyam API DOC ",
                version = "1.0",
                description = "API documentation for Abhedyam Application",
                license = @License(name = "License")
        ),
        servers = @Server(url = "/", description = "Default Server URL")

)
public class AbhedyamApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbhedyamApplication.class, args);

    }


}
