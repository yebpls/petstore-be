package com.bc03capstone.bc03cs.DTO;

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
    private ShipLocationDTO shipLocationDTO;
    private UserDTO userDTO;
    private List<OrderItemDTO> orderItemDTOList;
}
