package com.mtzz.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id //Primary key (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremento
    private Long id;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT")
    private Date dataCadastro; //not null

    @Column(nullable = false, length = 30)
    private String nome; //not null, varchar(30)

    @Column(nullable = false, length = 15, unique = true) //not null, varchar(15), Unique Key (UK)
    private String login;

    @Column(nullable = false, length = 10)
    private String senha; //not null, varchar(10)

    @Column(length = 11)
    private String telefone; //varchar (10) -> pode ser nulo

    @Column(length = 100)
    private String email; //varchar (100) -> pode ser nulo

    @Column(nullable = false, length = 1)
    private String perfil; // A - Administrador || O - OPerador

    @Column(nullable = false, length = 1)
    private String status; //A - Ativo || C - Cancelado


    public Usuario() {}

    public Usuario(Long id, Date dataCadastro,
                   String nome, String login, String senha,
                   String telefone, String email, String perfil,
                   String status) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.perfil = perfil;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(login, usuario.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }
}
