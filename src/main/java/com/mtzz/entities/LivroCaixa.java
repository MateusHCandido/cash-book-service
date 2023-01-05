package com.mtzz.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "LIVRO_CAIXA")
public class LivroCaixa implements Serializable {
    public static final long serializable = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    @Column(nullable = false)
    private Date dataLancamento;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, length = 1)
    private String tipo; //D-Débito ou C-Crédito

    @Column(nullable = false, precision = 10, scale = 2)
    private Double valor;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
