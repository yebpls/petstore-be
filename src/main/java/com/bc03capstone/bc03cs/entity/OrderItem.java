package com.bc03capstone.bc03cs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "orderItem")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "price")
    private String price;
    @Column(name = "status")
    private Boolean status;

    @JsonIgnoreProperties("orderItem")
    @OneToOne
    @JoinColumn(name = "petId")
    private Pet pet;

    @JsonIgnoreProperties("orderItem")
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orders;
}
