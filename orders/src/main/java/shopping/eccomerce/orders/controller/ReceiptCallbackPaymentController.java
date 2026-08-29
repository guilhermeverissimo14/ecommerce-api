package shopping.eccomerce.orders.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import shopping.eccomerce.orders.dto.ReceiptCallbackPaymentDTO;
import shopping.eccomerce.orders.service.OrdersService;

@RestController
@RequestMapping("/orders/callback-payments")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Criação de pedidos e pagamentos")
public class ReceiptCallbackPaymentController {

    private OrdersService ordersService;

    @Operation(summary = "Recebe o callback de status de pagamento do gateway")
    @ApiResponse(responseCode = "200", description = "Status de pagamento atualizado com sucesso")
    @PostMapping
    public ResponseEntity<Object> updatePayment(
        @Parameter(description = "Dados do callback de pagamento")
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
