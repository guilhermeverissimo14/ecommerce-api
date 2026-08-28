package shopping.eccomerce.orders.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shopping.eccomerce.orders.client.ServiceBankClient;
import shopping.eccomerce.orders.model.Orders;
import shopping.eccomerce.orders.model.enums.OrderStatus;
import shopping.eccomerce.orders.repository.OrdersItemRepository;
import shopping.eccomerce.orders.repository.OrdersRepository;
import shopping.eccomerce.orders.validator.OrdersValidator;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public void updateStatusPayment(Long orderCode, String paymentKey, boolean sucess, String observations) {
       var orderFound =  ordersRepository.findByCodeAndPaymentKey(orderCode, paymentKey);

       if(orderFound.isEmpty()){
        var msg = String.format("Pedido não encontrado para o código %d e chave pagamento %s", orderCode, paymentKey);
        log.error(msg);
        return;
       }

       Orders order = orderFound.get();

       if(sucess){
            order.setStatus(OrderStatus.PAGO);
       }else{
            order.setStatus(OrderStatus.ERRO_PAGAMENTO);
            order.setObservation(observations);
       }

       ordersRepository.save(order);
    }
    
    private void sendPayment(Orders orders) {
        var paymentKey = serviceBankClient.getPayment(orders);
        orders.setPaymentKey(paymentKey);
    }

}
