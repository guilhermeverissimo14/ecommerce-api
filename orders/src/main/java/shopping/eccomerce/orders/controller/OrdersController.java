package shopping.eccomerce.orders.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shopping.eccomerce.orders.dto.NewOrdersDTO;
import shopping.eccomerce.orders.dto.mappers.OrdersMapper;
import shopping.eccomerce.orders.service.OrdersService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    private final OrdersMapper ordersMapper;

    public ResponseEntity<Object> createOrder(@RequestBody NewOrdersDTO newOrdersDTO) {
        var orders = ordersMapper.map(newOrdersDTO);
        var createdOrder = ordersService.createOrder(orders);
        return ResponseEntity.ok(createdOrder.getCode());
    }
    
}
