package com.mtzz.resources;

import com.mtzz.entities.Usuario;
import com.mtzz.repositories.DTO.userDTO.EmailDTO;
import com.mtzz.repositories.DTO.userDTO.NameDTO;
import com.mtzz.repositories.DTO.userDTO.UserDTO;
import com.mtzz.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.naming.Name;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/users")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @PostMapping(value = "/create")
    public ResponseEntity<Usuario> create(@RequestBody Usuario user){
        user = service.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return created(uri).body(user);
    }

    @PutMapping(value = "/update/id/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario user){
        user.setId(id);
        service.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/id/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/id/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Usuario user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/login/{login}-{senha}")
    public ResponseEntity<Usuario> validarUsuario(@PathVariable String login,@PathVariable String senha){
        Usuario user = service.autenticarLogin(login, senha);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/filter/list/name-email")
    public ResponseEntity<List<UserDTO>> findAllByNameAndEmail(){
        List<UserDTO> users = service.findAllByNameAndEmail();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/filter/list/name")
    public ResponseEntity<List<NameDTO>> findAllByName(){
        List<NameDTO> users = service.findAllByName();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/filter/list/email")
    public ResponseEntity<List<EmailDTO>> findAllByEmail(){
        List<EmailDTO> users = service.findAllByEmail();
        return ResponseEntity.ok().body(users);
    }
}
