package com.bc03capstone.bc03cs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "species")
public class SpeciesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "species")
    private List<PetEntity> listPet;
}
