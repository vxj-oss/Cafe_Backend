package com.cafedebarrio.backend.dto;

import lombok.Data;

@Data
public class RegistroRequest {
    private String username;
    private String password;
}