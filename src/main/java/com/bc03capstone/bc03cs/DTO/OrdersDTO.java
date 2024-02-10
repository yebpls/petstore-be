package com.bc03capstone.bc03cs.DTO;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrdersDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String total;
    private String payLink;
    private String code;
    private LocalDateTime orderTime;
    private Boolean isCompleted;
    private Integer shipLocationId;
    private Integer userId;
}
