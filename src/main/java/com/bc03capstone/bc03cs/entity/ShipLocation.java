package com.bc03capstone.bc03cs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "shipLocation")
public class ShipLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "isDefault")
    private Boolean isDefault;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
