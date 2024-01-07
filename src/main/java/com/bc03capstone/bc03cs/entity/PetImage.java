package com.bc03capstone.bc03cs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity(name = "petImage")
public class PetImage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "status")
    private Boolean status;

    @JsonIgnoreProperties("petImage")
    @ManyToOne
    @JoinColumn(name = "petId")
    private Pet pet;
}
