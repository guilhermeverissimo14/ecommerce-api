package shopping.ecommerce.customers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import shopping.ecommerce.customers.model.Customers;
import shopping.ecommerce.customers.service.CustomersService;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Cadastro e consulta de clientes")
public class CustomersController {
    private final CustomersService customersService;

    @Operation(summary = "Cria um novo cliente")
    @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso")
    @PostMapping
    public ResponseEntity<Customers> createCustomers(@RequestBody Customers customers){
        customersService.saveCustomer(customers);
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Busca um cliente pelo código")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @GetMapping("{code}")
    public ResponseEntity<Customers> getCustomerCode(@PathVariable("code") Long code) {
        return customersService.getCustomerByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
