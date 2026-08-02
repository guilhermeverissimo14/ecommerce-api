package shopping.ecommerce.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.ecommerce.customers.model.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
    
}
