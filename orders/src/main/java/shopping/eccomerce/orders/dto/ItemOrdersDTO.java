package shopping.eccomerce.orders.dto;

public record ItemOrdersDTO(
    Long productCode, 
    Integer amount,
    Long unitPrice
) {
    
}
