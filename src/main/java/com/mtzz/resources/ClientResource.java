package com.mtzz.resources;

import com.mtzz.entities.Cliente;
import com.mtzz.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(value = "/create")
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente cliente){
        cliente = clienteService.createCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping(value = "update/client/id/{id}")
    public ResponseEntity<Cliente> updateData(@PathVariable Long id, @RequestBody Cliente cliente){
        cliente = clienteService.findById(id);
        cliente = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping(value = "delete/client/id/{id}")
    public ResponseEntity<Cliente> deleteClient(@PathVariable Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value =  "/find/client/id/{id}")
    public ResponseEntity<Cliente> findClientById(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }
}
