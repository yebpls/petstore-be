package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.OrdersDTO;
import com.bc03capstone.bc03cs.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrdersMapper {
    @Autowired
    private ModelMapper modelMapper;

    public OrdersDTO convertToDTO(Orders orders) {
        OrdersDTO ordersDTO = modelMapper.map(orders, OrdersDTO.class);
        ordersDTO.setShipLocationId(orders.getShipLocation().getId());
        ordersDTO.setUserId(orders.getUser().getId());
        return ordersDTO;
    }

    public Orders revertToEntity(OrdersDTO ordersDTO) {
        Orders orders = modelMapper.map(ordersDTO,Orders.class);
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setId(ordersDTO.getShipLocationId());
        orders.setShipLocation(shipLocation);
        User user = new User();
        user.setId(ordersDTO.getUserId());
        orders.setUser(user);
        orders.setStatus(true);
        return orders;
    }
}
