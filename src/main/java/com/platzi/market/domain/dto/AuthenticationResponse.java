package com.platzi.market.domain.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt; //json web token

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }


}
