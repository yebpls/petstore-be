package com.bc03capstone.bc03cs.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class SpeciesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Boolean status;
    @JsonIgnoreProperties("speciesDTO")
    private List<PetDTO> petDTOList;
}
