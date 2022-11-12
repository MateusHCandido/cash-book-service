package com.mtzz.repositories.DTO.userDTO;

import com.mtzz.entities.Usuario;

public class UserDTO {

    private Long id;
    private String name;
    private String email;

    public UserDTO(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDTO(Usuario user){
        id = user.getId();
        name = user.getNome();
        email = user.getEmail();
    }

    public UserDTO(String nome) {
        this.name = nome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
