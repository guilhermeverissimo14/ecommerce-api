package shopping.eccomerce.orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shopping.eccomerce.orders.client.representation.CustomerRepresentation;

@FeignClient(name = "customers", url = "${shopping.orders.customers.url}" )
public interface CustomersClient {
    @GetMapping("{code}")
    ResponseEntity<CustomerRepresentation> getCustomerCode(@PathVariable("code") Long code);
}
