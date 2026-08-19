package shopping.eccomerce.orders.dto.mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import shopping.eccomerce.orders.dto.ItemOrdersDTO;
import shopping.eccomerce.orders.dto.NewOrdersDTO;
import shopping.eccomerce.orders.model.OrderStatus;
import shopping.eccomerce.orders.model.Orders;
import shopping.eccomerce.orders.model.OrdersItem;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    OrdersItemMapper ORDER_ITEM_MAPPER = Mappers.getMapper(OrdersItemMapper.class);

    @Mapping(source = "items", target = "items", qualifiedByName = "mapItens")
    @Mapping(source = "paymentData", target = "paymentData")
    Orders map (NewOrdersDTO newOrdersDTO);

    @Named("mapItens")
    default List<OrdersItem> mapItens(List<ItemOrdersDTO> dtos){
        return dtos.stream().map(ORDER_ITEM_MAPPER::map).toList();
    }

    @AfterMapping
    default void afterMapping (@MappingTarget Orders orders){
        orders.setStatus(OrderStatus.REALIZADO);
        orders.setOrderDate(LocalDateTime.now());

        var total = calculateTotal(orders);

        orders.setTotal(total);
        orders.getItems().forEach(item -> item.setOrder(orders));
    }

    private static BigDecimal calculateTotal(Orders orders) {
        var total = orders.getItems().stream().map(item ->
             item.getUnitPrice().multiply(BigDecimal.valueOf(item.getAmount()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add).abs();
        return total;
    }
}
