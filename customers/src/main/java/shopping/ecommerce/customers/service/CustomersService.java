package shopping.ecommerce.customers.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shopping.ecommerce.customers.model.Customers;
import shopping.ecommerce.customers.repository.CustomersRepository;

@Service
@RequiredArgsConstructor
public class CustomersService {
    private final CustomersRepository customersRepository;

    public Customers saveCustomer(Customers customer) {
        return customersRepository.save(customer);
    }

    public Optional<Customers> getCustomerByCode(Long code) {
        return customersRepository.findById(code);
    }
}
