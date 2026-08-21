package shopping.eccomerce.orders.service.Config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "shopping.eccomerce.orders.client")
public class CustomerConfig {
    
}
