package com.bc03capstone.bc03cs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "petImage")
public class PetImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "petId")
    private Pet pet;
}