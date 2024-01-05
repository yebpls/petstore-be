package com.bc03capstone.bc03cs.DTO;

import com.bc03capstone.bc03cs.entity.Orders;
import com.bc03capstone.bc03cs.entity.Pet;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private BigDecimal price;
    private Boolean status;
    private Pet pet;
    private Orders orders;
}
