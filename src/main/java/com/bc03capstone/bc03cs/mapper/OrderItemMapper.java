package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.OrderItemDTO;
import com.bc03capstone.bc03cs.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public OrderItem revertToEntity(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OrderItemDTO orderItemDTO = objectMapper.readValue(jsonString, OrderItemDTO.class);
            OrderItem orderItem = modelMapper.map(orderItemDTO,OrderItem.class);
            Pet pet = new Pet();
            pet.setId(orderItemDTO.getPetId());
            orderItem.setPet(pet);
            Orders orders = new Orders();
            orders.setId(orderItemDTO.getOrderId());
            orderItem.setOrders(orders);
            orderItem.setStatus(true);
            return orderItem;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error revertToEntity OrderItem: " + e.getMessage());
        }
    }
}
