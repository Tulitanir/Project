package edu.nechaev.project.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Denis",
                        email = "myemail@mail.com"
                ),
                description = "Документация OpenAPI",
                title = "Спецификация OpenAPI - фитнесс-клуб",
                version = "1.0",
                license = @License(
                        name = "MIT License"
                )
        ),
        servers = {
                @Server(
                        description = "Локальная среда",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
