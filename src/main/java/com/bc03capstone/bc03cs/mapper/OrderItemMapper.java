package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.OrderItemDTO;
import com.bc03capstone.bc03cs.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    @Autowired
    private ModelMapper modelMapper;

    public OrderItemDTO convertToDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = modelMapper.map(orderItem, OrderItemDTO.class);
        orderItemDTO.setPetId(orderItem.getPet().getId());
        orderItemDTO.setOrderId(orderItem.getOrders().getId());
        return orderItemDTO;
    }

    public OrderItem revertToEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = modelMapper.map(orderItemDTO,OrderItem.class);
        Pet pet = new Pet();
        pet.setId(orderItemDTO.getPetId());
        orderItem.setPet(pet);
        Orders orders = new Orders();
        orders.setId(orderItemDTO.getOrderId());
        orderItem.setOrders(orders);
        orderItem.setStatus(true);
        return orderItem;
    }
}
