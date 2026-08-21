package shopping.eccomerce.orders.client.representation;

import java.math.BigDecimal;

public record ProductRepresentation(Long code, String name, BigDecimal unitPrice) {
    
}
