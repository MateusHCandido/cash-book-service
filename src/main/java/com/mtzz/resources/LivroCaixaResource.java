package com.mtzz.resources;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mtzz.entities.Cliente;
import com.mtzz.entities.DTOs.LivroCaixaDTO;
import com.mtzz.entities.LivroCaixa;
import com.mtzz.services.ClienteService;
import com.mtzz.services.LivroCaixaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/livro-caixa")
public class LivroCaixaResource {

    @Autowired
    private LivroCaixaService service;

    @Autowired
    private ClienteService clienteService;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody LivroCaixaDTO livroCaixaDTO){
        LivroCaixa livroCaixa = new LivroCaixa();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(livroCaixaDTO.getId()).toUri();
        BeanUtils.copyProperties(livroCaixaDTO, livroCaixa);
        if(livroCaixaDTO.getId_cliente() != null) {
            livroCaixa.setCliente(clienteService.findById(livroCaixaDTO.getId_cliente()));
        }
        return ResponseEntity.created(uri).body(service.create(livroCaixa));
    }

    @GetMapping(value = "/find/id/{id}")
    public ResponseEntity<Optional<LivroCaixa>> findLivroCaixaById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping(value = "/update/id/{id}")
    public ResponseEntity<LivroCaixa> update(@PathVariable Long id, @RequestBody LivroCaixa lc){
        lc.setId(id);
        service.update(lc);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/id/{id}")
    public ResponseEntity<LivroCaixa> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @JacksonInject
    @GetMapping(value = "/list/by/id/{id}")
    public ResponseEntity<List<LivroCaixa>> findByClienteId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findByCliente(id));
    }

}
