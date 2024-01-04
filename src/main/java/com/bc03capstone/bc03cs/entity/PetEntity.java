package com.bc03capstone.bc03cs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "pet")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "breed")
    private String breed;
    @Column(name = "listPrice")
    private float listPrice;
    @Column(name = "salePercent")
    private int salePercent;
    @Column(name = "taxIncluded")
    private float taxIncluded;
    @Column(name = "uploadDate")
    private Date uploadDate;
    @Column(name = "age")
    private String age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "color")
    private String color;
    @Column(name = "weight")
    private String weight;
    @Column(name = "country")
    private String country;
    @Column(name = "description")
    private String description;
    @Column(name = "state")
    private Boolean state;
    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "speciesId")
    private SpeciesEntity species;

    @OneToMany(mappedBy = "pet")
    private List<PetImageEntity> listPetImage;
}
