package com.serasa.desafio.model;

public class JwtResponse {

    private String jwtToken;

    public JwtResponse() {
    }

    public JwtResponse(final String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(final String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
