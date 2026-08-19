package shopping.eccomerce.orders.dto.mappers;

import org.mapstruct.Mapper;

import shopping.eccomerce.orders.dto.ItemOrdersDTO;
import shopping.eccomerce.orders.model.OrdersItem;

@Mapper(componentModel = "spring")
public interface OrdersItemMapper {
    OrdersItem map(ItemOrdersDTO orderItem);
}
