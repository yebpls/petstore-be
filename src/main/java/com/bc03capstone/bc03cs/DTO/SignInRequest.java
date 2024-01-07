package com.bc03capstone.bc03cs.DTO;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
