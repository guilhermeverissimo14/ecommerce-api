package shopping.ecommerce.customers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shopping.ecommerce.customers.model.Customers;
import shopping.ecommerce.customers.service.CustomersService;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomersController {
    private final CustomersService customersService;

    @PostMapping
    public ResponseEntity<Customers> createCustomers(@RequestBody Customers customers){
        customersService.saveCustomer(customers);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("{code}")
    public ResponseEntity<Customers> getCustomerCode(@PathVariable("code") Long code) {
        return customersService.getCustomerByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
