package com.mtzz.repositories;

import com.mtzz.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{

    Usuario findByLoginAndSenha(String login, String senha);

    List<Usuario> findByNomeAndEmail(String nome, String email);

    List<Usuario> findByNome(String nome);

    List<Usuario> findByEmail(String email);
}
