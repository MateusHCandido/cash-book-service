package com.mtzz.repositories.DTO.userDTO;

import com.mtzz.entities.Usuario;

import java.io.Serializable;

public class NameDTO{


    private String name;


    public NameDTO(String name){
        this.name = name;
    }

    public NameDTO(Usuario user){
        name = user.getNome();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
