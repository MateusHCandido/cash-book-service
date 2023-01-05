package com.mtzz.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "CLIENTES")
public class Cliente implements Serializable {
    private final static long serializable = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremento
    private Long id;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT")
    private Date dataCadastro;

    @Column(nullable = false, length = 30)
    private String nome;

    @Column(nullable = false, length = 14, name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(nullable = false, length = 50)
    private String logradouro;

    @Column(nullable = false, length = 40)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String uf;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(length = 11)
    private String telefone;

    @Column(length = 100)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<LivroCaixa> contabil = new HashSet<>();

}
