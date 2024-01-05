package com.bc03capstone.bc03cs.DTO;

import com.bc03capstone.bc03cs.entity.Cart;
import com.bc03capstone.bc03cs.entity.Orders;
import com.bc03capstone.bc03cs.entity.ShipLocation;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String fullName;
    private String email;
    private LocalDate birthday;
    private String gender;
    private LocalDate createDate;
    private String avatarUrl;
    private String phoneNumber;
    private String password;
    private String role;
    private boolean status;
    private Cart cart;
    private List<ShipLocation> listShipLocation;
    private List<Orders> listOrders;
}
