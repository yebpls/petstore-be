package com.bc03capstone.bc03cs.DTO;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrdersDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer userId;

    private Integer shipLocationId;

    private BigDecimal total;

    private String payLink;

    private String code;

    private Date orderTime;

    private Integer state;

    private Integer status;

}
