package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.CartDTO;
import com.bc03capstone.bc03cs.entity.Cart;
import com.bc03capstone.bc03cs.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        cartDTO.setUserId(cart.getUser().getId());
        return cartDTO;
    }

    public Cart revertToEntity(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CartDTO cartDTO = objectMapper.readValue(jsonString, CartDTO.class);
            Cart cart = modelMapper.map(cartDTO,Cart.class);
            User user = new User();
            user.setId(cartDTO.getUserId());
            cart.setUser(user);
            cart.setStatus(true);
            return cart;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error revertToEntity cart: " + e.getMessage());
        }

    }
}
