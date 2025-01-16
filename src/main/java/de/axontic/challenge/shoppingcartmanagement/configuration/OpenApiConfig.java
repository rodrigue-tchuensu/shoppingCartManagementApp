package de.axontic.challenge.shoppingcartmanagement.configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                version = "1.0.0",
                title = "",
                description = "** API documentation for the ShoppingCartManagementSystem**",
                contact = @Contact(
                        name = "Rodrigue TCHUENSU POUOPSE",
                        url = "",
                        email = "pouopse@yahoo.fr"
                )),
        servers = @Server(url = "http://localhost:8080")
)
public class OpenApiConfig {
}
