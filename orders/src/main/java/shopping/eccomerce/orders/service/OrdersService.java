package shopping.eccomerce.orders.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import shopping.eccomerce.orders.client.ServiceBankClient;
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
    private final ServiceBankClient serviceBankClient;

    @Transactional
    public Orders createOrder(Orders orders) {
        ordersValidator.validate(orders);
        ordersRepository.save(orders);
        ordersItemRepository.saveAll(orders.getItems());
        sendPayment(orders);
        return orders;
    }

    private void sendPayment(Orders orders) {
        var paymentKey = serviceBankClient.getPayment(orders);
        orders.setPaymentKey(paymentKey);
    }
}
