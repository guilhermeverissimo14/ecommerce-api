package shopping.eccomerce.orders.validator;

import org.springframework.stereotype.Component;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shopping.eccomerce.orders.client.CustomersClient;
import shopping.eccomerce.orders.client.ProductsClient;
import shopping.eccomerce.orders.client.representation.CustomerRepresentation;
import shopping.eccomerce.orders.client.representation.ProductRepresentation;
import shopping.eccomerce.orders.model.Orders;
import shopping.eccomerce.orders.model.OrdersItem;
import shopping.eccomerce.orders.model.exception.ValidationException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrdersValidator {

    private final ProductsClient productsClient;
    private final CustomersClient customersClient;

    public void validate(Orders orders) {
        Long customerCode = orders.getCustomerCode();
        customerValidate(customerCode);
        orders.getItems().forEach(this::itemValidate);
    }

    private void customerValidate(Long customerCode) {

        try {
            var response = customersClient.getCustomerCode(customerCode);
            CustomerRepresentation customer = response.getBody();
            log.info("Cliente de código {} encontrado: {}", customer.code(), customer.name());
        } catch (FeignException.NotFound e) {
            var message = String.format("Cliente de codigo %d não encontrado", customerCode);
            throw new ValidationException("customerCode", message);
        }

    }

    private void itemValidate(OrdersItem item) {
        try {
            var response = productsClient.getProductCode(item.getCode());
            ProductRepresentation product = response.getBody();
            log.info("Produto de código {} encontrado: {}", product.code(), product.name());
        } catch (FeignException.NotFound e) {
            var message = String.format("Produto de codigo %d não encontrado", item.getCode());
            throw new ValidationException("code", message);
        }
    }
}
