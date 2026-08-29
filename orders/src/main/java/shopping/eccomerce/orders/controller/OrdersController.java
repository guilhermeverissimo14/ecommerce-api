package shopping.eccomerce.orders.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Orders", description = "Criação de pedidos e pagamentos")
public class OrdersController {

    private final OrdersService ordersService;
    private final OrdersMapper ordersMapper;

    @Operation(summary = "Cria um novo pedido")
    @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Cliente ou produto inválido",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
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

    @Operation(summary = "Adiciona um pagamento a um pedido existente")
    @ApiResponse(responseCode = "204", description = "Pagamento registrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Pedido não encontrado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping("payments/{orderCode}")
    public ResponseEntity<Object> createNewPayment(@PathVariable Long orderCode, @RequestBody AddNewPaymentDTO newPaymentDTO ){

        try {
            ordersService.addNewPayment(orderCode, newPaymentDTO.dataCard(), newPaymentDTO.paymentType());
        } catch (Exception e) {
           var error = new ErrorResponse("Item não encontrado", "orderCode", e.getMessage());
           return ResponseEntity.badRequest().body(error);
        }


        return ResponseEntity.noContent().build(); //retorna codigo 204.
    }
    
}
