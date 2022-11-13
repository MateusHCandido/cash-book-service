package com.mtzz.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CLIENTES")
public class Cliente {

    @Id //Primary key (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremento
    private Long id;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT")
    private Date dataCadastro; //not null

    @Column(nullable = false, length = 30)
    private String nome; //not null, varchar(30)

    @Column(nullable = false, length = 14, name = "CPF_CNPJ")
    private String cpfCnpj; //not null, varchar (14)

    @Column(nullable = false, length = 50)
    private String logradouro; //not null, varchar(50)

    @Column(nullable = false, length = 40)
    private String cidade; //not null, varchar(40)

    @Column(nullable = false, length = 2)
    private String uf; //not null, varchar(2)

    @Column(nullable = false, length = 8)
    private String cep; //not null, varchar(8)

    @Column(length = 11)
    private String telefone; //varchar(11)

    @Column(length = 100)
    private String email; //varchar(100)


    public Cliente() {
    }

    public Cliente(Long id, Date dataCadastro, String nome, String cpfCnpj, String logradouro, String cidade, String uf, String cep, String telefone, String email) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //cash-book-service
}
