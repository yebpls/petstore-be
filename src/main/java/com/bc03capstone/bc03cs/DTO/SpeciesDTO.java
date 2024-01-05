package com.bc03capstone.bc03cs.DTO;

import com.bc03capstone.bc03cs.entity.Pet;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class SpeciesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Boolean status;
    private List<Pet> listPet;
}
