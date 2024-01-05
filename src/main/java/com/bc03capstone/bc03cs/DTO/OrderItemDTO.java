package com.bc03capstone.bc03cs.DTO;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer petId;

    private Integer orderId;

    private BigDecimal price;

    private Integer status;

}
