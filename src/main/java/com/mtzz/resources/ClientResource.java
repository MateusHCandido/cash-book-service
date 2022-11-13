package com.mtzz.resources;

import com.mtzz.entities.Cliente;
import com.mtzz.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClienteService service;

    @PostMapping(value = "/create")
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente cliente){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(service.createCliente(cliente));
    }

    @GetMapping(value =  "/find/client/id/{id}")
    public ResponseEntity<Cliente> findClientById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping(value = "update/client/id/{id}")
    public ResponseEntity<Cliente> updateData(@PathVariable Long id, @RequestBody Cliente cliente){
        cliente.setId(id);
        service.updateCliente(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "delete/client/id/{id}")
    public ResponseEntity<Cliente> deleteClient(@PathVariable Long id){
        service.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/filter/list/name-cpfCnpj-cidade-uf")
    public ResponseEntity<List<Map<String, String>>> findAllByNCCU(){
        return ResponseEntity.ok().body(service.findAllByNCCU());
    }

    @GetMapping(value = "/filter/list/name")
    public ResponseEntity<List<Map<String, String>>> findAllByName(){
        return ResponseEntity.ok().body(service.findAllByName());
    }

    @GetMapping(value = "/filter/list/cpf_cnpj")
    public ResponseEntity<List<Map<String, String>>> findAllByCpfCnpj(){
        return ResponseEntity.ok().body(service.findAllByCpfCnpj());
    }

    @GetMapping(value = "/filter/list/cidade")
    public ResponseEntity<List<Map<String, String>>> findAllByCidade(){
        return ResponseEntity.ok().body(service.findAllByCidade());
    }

    @GetMapping(value = "/filter/list/uf")
    public ResponseEntity<List<Map<String, String>>> findAllByUF(){
        return ResponseEntity.ok().body(service.findAllByUF());
    }
}
