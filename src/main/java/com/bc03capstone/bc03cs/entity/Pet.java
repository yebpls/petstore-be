package com.bc03capstone.bc03cs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "pet")
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "breed")
    private String breed;
    @Column(name = "listPrice")
    private String listPrice;
    @Column(name = "salePercent")
    private Integer salePercent;
    @Column(name = "taxIncluded")
    private String taxIncluded;
    @Column(name = "uploadDate")
    private LocalDate uploadDate;
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
    @Column(name = "mainImage")
    private String mainImage;
    @Column(name = "isSold")
    private Boolean isSold;
    @Column(name = "status")
    private Boolean status;

    @JsonIgnoreProperties("pet")
    @ManyToOne
    @JoinColumn(name = "speciesId")
    private Species species;

    @JsonIgnoreProperties("pet")
    @OneToMany(mappedBy = "pet")
    private List<PetImage> petImageList;
}
