package com.mtzz.repositories;

import com.mtzz.entities.Cliente;
import com.mtzz.entities.LivroCaixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroCaixaRepository extends JpaRepository<LivroCaixa, Long> {

    List<LivroCaixa> findByCliente_id(Long cliente);
}
