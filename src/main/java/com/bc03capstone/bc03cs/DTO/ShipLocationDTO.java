package com.bc03capstone.bc03cs.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class ShipLocationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer userId;

    private String address;

    private String phoneNumber;

    private Integer isDefault;

    private Integer status;

}
