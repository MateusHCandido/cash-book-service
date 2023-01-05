package com.mtzz.entities.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ClienteDTO {

    private Long id;
    private Date dataCadastro;
    private String nome;
    private String cpfCnpj;
    private String logradouro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String email;

}
