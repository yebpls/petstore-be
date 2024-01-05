package com.bc03capstone.bc03cs.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class SpeciesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private Integer status;

}
