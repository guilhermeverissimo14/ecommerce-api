package shopping.eccomerce.orders.dto;

import shopping.eccomerce.orders.model.enums.PaymentType;

public record PaymentDataDTO(
    String data,
    PaymentType paymentType
) {
    
}
