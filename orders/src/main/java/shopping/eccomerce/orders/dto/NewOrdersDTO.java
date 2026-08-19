package shopping.eccomerce.orders.dto;

import java.util.List;

public record NewOrdersDTO(
    Long customerCode,
    PaymentDataDTO paymentData,
    List<ItemOrdersDTO> items
) {
    
}
