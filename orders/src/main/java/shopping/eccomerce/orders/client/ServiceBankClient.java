package shopping.eccomerce.orders.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import shopping.eccomerce.orders.model.Orders;

@Component
@Slf4j
public class ServiceBankClient {
    public String getPayment(Orders orders){
        log.info("Solicitando pagamento para o pedido de código: {}.", orders.getCode());
        return UUID.randomUUID().toString();
    }
}
