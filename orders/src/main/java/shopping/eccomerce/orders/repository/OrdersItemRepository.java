package shopping.eccomerce.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.eccomerce.orders.model.OrdersItem;

public interface OrdersItemRepository extends JpaRepository<OrdersItem, Long> {
    
}
