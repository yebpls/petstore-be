package com.bc03capstone.bc03cs.DTO;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String fullName;
    private String email;
    private LocalDate birthday;
    private String gender;
    private LocalDate createDate;
    private String avatarUrl;
    private String phoneNumber;
    private String password;
    private String role;
    private String country;
}
