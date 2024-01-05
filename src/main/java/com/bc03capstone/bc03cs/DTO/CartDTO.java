package com.bc03capstone.bc03cs.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class CartDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer userId;

    private Integer status;

}
