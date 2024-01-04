package com.bc03capstone.bc03cs.payload;

import lombok.Data;

@Data
public class BaseResponse {
    private int statusCode = 200;
    private String message = "";
    private Object data;
}
