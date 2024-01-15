package com.bc03capstone.bc03cs.DTO;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class PetDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String breed;
    private String listPrice;
    private Integer salePercent;
    private String taxIncluded;
    private LocalDate uploadDate;
    private String age;
    private String gender;
    private String color;
    private String weight;
    private String country;
    private String description;
    private String mainImage;
    private Boolean state;

    private Integer speciesID;
    private List<Integer> petImageIdList;
}
