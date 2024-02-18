package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.CartItemDTO;
import com.bc03capstone.bc03cs.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CartItemDTO convertToDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = modelMapper.map(cartItem, CartItemDTO.class);
        cartItemDTO.setPetId(cartItem.getPet().getId());
        cartItemDTO.setCartId(cartItem.getCart().getId());
        return cartItemDTO;
    }

    public CartItem revertToEntity(String jsonString)  {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CartItemDTO cartItemDTO = objectMapper.readValue(jsonString, CartItemDTO.class);
            CartItem cartItem = modelMapper.map(cartItemDTO,CartItem.class);
            Pet pet = new Pet();
            pet.setId(cartItemDTO.getPetId());
            cartItem.setPet(pet);
            Cart cart = new Cart();
            cart.setId(cartItemDTO.getCartId());
            cartItem.setCart(cart);
            cartItem.setStatus(true);
            return cartItem;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error revertToEntity cartItem: " + e.getMessage());
        }
    }
}
