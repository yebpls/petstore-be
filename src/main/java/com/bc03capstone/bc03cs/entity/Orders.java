package com.bc03capstone.bc03cs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "payLink")
    private String payLink;

    @Column(name = "code")
    private String code;

    @Column(name = "orderTime")
    private LocalDateTime orderTime;

    @Column(name = "state")
    private Boolean state;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "shipLocationId")
    private ShipLocation shipLocation;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItemList;
}
