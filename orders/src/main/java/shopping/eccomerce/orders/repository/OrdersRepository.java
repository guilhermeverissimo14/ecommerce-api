package shopping.eccomerce.orders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.eccomerce.orders.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findByCodeAndPaymentKey(Long code, String paymentKey);
    
} 