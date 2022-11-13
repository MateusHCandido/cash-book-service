package com.mtzz.repositories;

import com.mtzz.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{

    Usuario findByLoginAndSenha(String login, String senha);
}
