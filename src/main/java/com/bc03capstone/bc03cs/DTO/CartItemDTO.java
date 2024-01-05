package com.bc03capstone.bc03cs.DTO;


import com.bc03capstone.bc03cs.entity.Cart;
import com.bc03capstone.bc03cs.entity.Pet;
import lombok.Data;
import java.io.Serializable;

@Data
public class CartItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Boolean status;
    private Pet pet;
    private Cart cart;
}
