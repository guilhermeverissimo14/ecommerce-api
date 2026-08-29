package shopping.eccomerce.orders.dto;

import shopping.eccomerce.orders.model.enums.PaymentType;

public record AddNewPaymentDTO(
    String dataCard ,PaymentType paymentType
) {
    
}
