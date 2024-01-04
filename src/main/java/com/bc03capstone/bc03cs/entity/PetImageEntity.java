package com.bc03capstone.bc03cs.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "petImage")
public class PetImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "petId")
    private PetEntity pet;
}
