package shopping.eccomerce.orders.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shopping.eccomerce.orders.dto.ReceiptCallbackPaymentDTO;
import shopping.eccomerce.orders.service.OrdersService;

@RestController
@RequestMapping("/orders/callback-payments")
@RequiredArgsConstructor
public class ReceiptCallbackPaymentController {
    
    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<Object> updatePayment(
        @RequestBody ReceiptCallbackPaymentDTO body,
        @RequestHeader(required = true, name = "apiKey") String apiKey
    ){
        ordersService.updateStatusPayment(
            body.code(),
            body.paymentKey(),
            body.status(),
            body.observations()
        );
        return ResponseEntity.ok().build();
    }
}
