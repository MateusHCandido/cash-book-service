package com.mtzz.resources;

import com.mtzz.entities.Usuario;
import com.mtzz.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

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
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value = "/login/{login}-{senha}")
    public ResponseEntity<Usuario> validarUsuario(@PathVariable String login,@PathVariable String senha){
        return ResponseEntity.ok().body(service.autenticarLogin(login, senha));
    }

    @GetMapping(value = "/filter/list/name-email/{nome}-{email}")
    public ResponseEntity<List<Usuario>> findAllByNameAndEmail(@PathVariable String nome, @PathVariable String email){
        return ResponseEntity.ok().body(service.findAllByNameAndEmail(nome, email));
    }

    @GetMapping(value = "/filter/list/name/{nome}")
    public ResponseEntity<List<Usuario>> findAllByName(@PathVariable String nome){
        return ResponseEntity.ok().body(service.findAllByName(nome));
    }

    @GetMapping(value = "/filter/list/email/{email}")
    public ResponseEntity<List<Usuario>> findAllByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(service.findAllByEmail(email));
    }

}
