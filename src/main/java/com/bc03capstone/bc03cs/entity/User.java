package com.bc03capstone.bc03cs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "gender")
    private String gender;
    @Column(name = "createDate")
    private LocalDate createDate;
    @Column(name = "avatarUrl")
    private String avatarUrl;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "status")
    private boolean status;

    @JsonIgnoreProperties("user")
    @OneToOne(mappedBy = "user")
    private Cart cart;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<ShipLocation> shipLocationList;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Orders> ordersList;
}
