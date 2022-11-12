package com.mtzz.repositories.DTO.userDTO;

import com.mtzz.entities.Usuario;

public class EmailDTO {

    private String email;

    public EmailDTO(String email){
        this.email = email;
    }

    public EmailDTO(Usuario u){
        email = u.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
