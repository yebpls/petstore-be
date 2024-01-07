package com.bc03capstone.bc03cs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Integer id;
    @Column(name = "status")
    private Boolean status;

    @JsonIgnoreProperties("cart")
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItemList;

    @JsonIgnoreProperties("cart")
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}
