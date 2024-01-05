package com.bc03capstone.bc03cs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity(name = "orderItem")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "petId")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orders;
}
