package com.bc03capstone.bc03cs.DTO;

import lombok.Data;
import java.io.Serializable;

@Data
public class ShipLocationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String address;
    private String phoneNumber;
    private Boolean isDefault;
    private Boolean status;
    private UserDTO userDTO;
}
