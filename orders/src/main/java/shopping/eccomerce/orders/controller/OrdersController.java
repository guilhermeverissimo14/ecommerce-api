package shopping.eccomerce.orders.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shopping.eccomerce.orders.dto.AddNewPaymentDTO;
import shopping.eccomerce.orders.dto.NewOrdersDTO;
import shopping.eccomerce.orders.dto.mappers.OrdersMapper;
import shopping.eccomerce.orders.model.ErrorResponse;
import shopping.eccomerce.orders.model.exception.ValidationException;
import shopping.eccomerce.orders.service.OrdersService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    private final OrdersMapper ordersMapper;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody NewOrdersDTO newOrdersDTO) {
        try {       
            var orders = ordersMapper.map(newOrdersDTO);
            var createdOrder = ordersService.createOrder(orders);
            return ResponseEntity.ok(createdOrder.getCode());
        } catch (ValidationException e) {
            var error = new ErrorResponse("Erro validação", e.getField(), e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    @PostMapping("payments/{orderCode}")
    public ResponseEntity<Object> createNewPayment(@PathVariable Long orderCode, @RequestBody AddNewPaymentDTO newPaymentDTO ){
        ordersService.addNewPayment(orderCode, newPaymentDTO.dataCard(), newPaymentDTO.paymentType());

        return ResponseEntity.noContent().build(); //retorna codigo 204.
    }
    
}
