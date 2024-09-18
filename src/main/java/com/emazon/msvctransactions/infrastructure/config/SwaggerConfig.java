package com.emazon.msvctransactions.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;


@OpenAPIDefinition(
        info = @Info(
                title = "Stock Microservice API",
                description = "Stock Microservice API Documentation contains Category, Brands, Articles",
                version = "1.0.0",
                contact = @Contact(
                        name = "Juan Gerardo Méndez López",
                        email = "juange.mendez.lopez@gmail.com")
        ),
        servers = {
                @Server(url = "http://localhost:8090", description = "Local Dev Server"),
        },
        security = @SecurityRequirement(name = "JWT Token")
)
@SecurityScheme(
        name= "JWT Token",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"

)
public class SwaggerConfig {
}
