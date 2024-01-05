package com.bc03capstone.bc03cs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "cartItem")
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "petId")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

}
