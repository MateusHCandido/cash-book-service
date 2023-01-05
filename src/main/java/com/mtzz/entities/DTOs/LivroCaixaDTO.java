package com.mtzz.entities.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class LivroCaixaDTO {

    private Long id;
    private Long id_cliente;
    private Date dataLancamento;
    private String descricao;
    private String tipo;
    private Double valor;

}
