package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.OrderLineItemDTO;
import com.service.foodorderserviceserver.Entity.OrderLineItem;

public class OrderLineItemMapper {

    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;

    public OrderLineItemMapper(OrderMapper orderMapper,
                               ItemMapper itemMapper) {
        this.orderMapper = orderMapper;
        this.itemMapper = itemMapper;
    }
    public OrderLineItemDTO convertToDto(OrderLineItem orderLineItem) {
        OrderLineItemDTO orderLineItemDTO = new OrderLineItemDTO();
        orderLineItemDTO.setId(orderLineItem.getId());
        orderLineItemDTO.setOrderId(orderLineItem.getOrder() != null
                ? orderMapper.convertToDto(orderLineItem.getOrder())
                : null);
        orderLineItemDTO.setProductId(orderLineItem.getItemId() != null
                ? itemMapper.convertToDto(orderLineItem.getItemId())
                : null);
        orderLineItemDTO.setQuantity(orderLineItem.getQuantity());
        orderLineItemDTO.setTotalPrice(orderLineItem.getPrice());
        return orderLineItemDTO;
    }
}
