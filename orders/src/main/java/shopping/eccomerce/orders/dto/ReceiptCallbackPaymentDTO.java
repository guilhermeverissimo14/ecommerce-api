package shopping.eccomerce.orders.dto;

public record ReceiptCallbackPaymentDTO(
    Long code,
    String paymentKey,
    boolean status,
    String observations
) {
    
}
