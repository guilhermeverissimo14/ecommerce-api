package shopping.eccomerce.orders.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shopping.eccomerce.orders.model.Orders;
import shopping.eccomerce.orders.repository.OrdersItemRepository;
import shopping.eccomerce.orders.repository.OrdersRepository;
import shopping.eccomerce.orders.validator.OrdersValidator;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrdersItemRepository ordersItemRepository;
    private final OrdersValidator ordersValidator;

    public Orders createOrder(Orders orders) {
        ordersRepository.save(orders);
        ordersItemRepository.saveAll(orders.getItems());
        return orders;
    }
}
