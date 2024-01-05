package com.bc03capstone.bc03cs.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class PetDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String breed;
    private BigDecimal listPrice;
    private int salePercent;
    private BigDecimal taxIncluded;
    private LocalDate uploadDate;
    private String age;
    private String gender;
    private String color;
    private String weight;
    private String country;
    private String description;
    private Boolean state;
    private Boolean status;
    @JsonIgnoreProperties("petDTOList")
    private SpeciesDTO speciesDTO;
    @JsonIgnoreProperties("petDTO")
    private List<PetImageDTO> petImageDTOList;
}
