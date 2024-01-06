package com.bc03capstone.bc03cs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "cart")
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItemList;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}
