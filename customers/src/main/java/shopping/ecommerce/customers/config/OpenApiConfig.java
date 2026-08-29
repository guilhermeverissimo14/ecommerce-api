package shopping.ecommerce.customers.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customersOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Customers API")
                        .description("Endpoints para cadastro e consulta de clientes")
                        .version("v1"));
    }
}
