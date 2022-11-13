package com.mtzz.repositories;

import com.mtzz.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeAndCpfCnpjAndCidadeAndUf(String nome, String cpfCnpj, String cidade, String uf);//ok

    List<Cliente> findByNomeAndCpfCnpjAndCidade(String nome, String cpfCnpj, String cidade);//ok

    List<Cliente> findByNomeAndCpfCnpjAndUf(String nome,String cpfCnpj, String uf);//ok

    List<Cliente> findByNomeAndCidadeAndUf(String nome, String cidade, String uf);

    List<Cliente> findByNomeAndCpfCnpj(String nome, String cpfCnpj);

    List<Cliente> findByNomeAndCidade(String nome, String cidade);

    List<Cliente> findByNomeAndUf(String nome, String uf);

    List<Cliente> findByCpfCnpjAndCidade(String CpfCnpj, String cidade);

    List<Cliente> findByCpfCnpjAndUf(String CpfCnpj, String uf);

    List<Cliente> findByCidadeAndUf(String cidade, String uf);

    List<Cliente> findByCpfCnpj(String cpfCnpj);

    List<Cliente> findByCidade(String cidade);

    List<Cliente> findByUf(String uf);


}
