package com.bc03capstone.bc03cs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "orders")
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "total")
    private String total;
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

    @JsonIgnoreProperties("orders")
    @OneToOne
    @JoinColumn(name = "shipLocationId")
    private ShipLocation shipLocation;

    @JsonIgnoreProperties("orders")
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @JsonIgnoreProperties("orders")
    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItemList;
}
