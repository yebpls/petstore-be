package com.bc03capstone.bc03cs.DTO;


import com.bc03capstone.bc03cs.entity.CartItem;
import com.bc03capstone.bc03cs.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CartDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private User user;
    private Boolean status;
    private List<CartItem> listCartItem;
}
