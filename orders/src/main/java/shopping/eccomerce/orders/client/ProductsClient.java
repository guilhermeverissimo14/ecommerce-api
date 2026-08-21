package shopping.eccomerce.orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shopping.eccomerce.orders.client.representation.ProductRepresentation;

@FeignClient(name = "products", url = "${shopping.orders.customers.products.url}" )
public interface ProductsClient {

 @GetMapping("{code}")
 ResponseEntity<ProductRepresentation> getProductCode(@PathVariable("code") Long code);
}
