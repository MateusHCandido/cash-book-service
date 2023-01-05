package com.mtzz.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "USUARIO")
public class Usuario implements Serializable {
    public static final long serializable = 1L;

    @EqualsAndHashCode.Include
    @Id //Primary key (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT")
    private Date dataCadastro;

    @Column(nullable = false, length = 30)
    private String nome;

    @Column(nullable = false, length = 15, unique = true)
    private String login;

    @Column(nullable = false, length = 10)
    private String senha;

    @Column(length = 11)
    private String telefone;

    @Column(length = 100)
    private String email;

    @Column(nullable = false, length = 1)
    private String perfil; // A - Administrador || O - OPerador

    @Column(nullable = false, length = 1)
    private String status; //A - Ativo || C - Cancelado
}
