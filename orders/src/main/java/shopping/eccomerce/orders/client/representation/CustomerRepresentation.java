package shopping.eccomerce.orders.client.representation;

public record CustomerRepresentation(
    Long code, 
    String name, 
    String cpf, 
    String street,
    String number, 
    String neighborhood, 
    String email, 
    String phone) {

}
