package com.bc03capstone.bc03cs.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;

@Data
public class PetImageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String imageUrl;
    private Boolean status;
    @JsonIgnoreProperties("petImageDTOList")
    private PetDTO petDTO;
}
