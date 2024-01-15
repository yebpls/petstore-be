package com.bc03capstone.bc03cs.DTO;

import lombok.Data;
import java.io.Serializable;

@Data
public class OrderItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String price;

    private Integer petId;
    private Integer orderId;
}
