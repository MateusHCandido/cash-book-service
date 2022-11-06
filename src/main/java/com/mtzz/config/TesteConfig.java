package com.mtzz.config;

import com.mtzz.entities.Usuario;
import com.mtzz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;



    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Usuario u1 = new Usuario(null, sdf.parse("2022-10-31 18:10:22"), "Mateus Henrique", "mateushcds",
                "327723", "81984143332", "mateus@teste.com", "A", "C");
        userRepository.save(u1);
    }
}
