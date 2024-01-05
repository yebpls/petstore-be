package com.bc03capstone.bc03cs.DTO;

import com.bc03capstone.bc03cs.entity.OrderItem;
import com.bc03capstone.bc03cs.entity.ShipLocation;
import com.bc03capstone.bc03cs.entity.User;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdersDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private BigDecimal total;
    private String payLink;
    private String code;
    private LocalDateTime orderTime;
    private Boolean state;
    private Boolean status;
    private ShipLocation shipLocation;
    private User user;
    private List<OrderItem> listOrderItem;
}
