package com.bc03capstone.bc03cs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "pet")
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "breed")
    private String breed;
    @Column(name = "listPrice")
    private BigDecimal listPrice;
    @Column(name = "salePercent")
    private int salePercent;
    @Column(name = "taxIncluded")
    private BigDecimal taxIncluded;
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
    @Column(name = "state")
    private Boolean state;
    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "speciesId")
    private Species species;

    @OneToMany(mappedBy = "pet")
    private List<PetImage> listPetImage;
}
