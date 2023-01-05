package com.mtzz.resources;

import com.mtzz.entities.Cliente;
import com.mtzz.entities.DTOs.ClienteDTO;
import com.mtzz.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClienteResource {

    @Autowired
    private ClienteService service;


    @PostMapping(value = "/create")
    public ResponseEntity<Cliente> createClient(@RequestBody ClienteDTO clienteDTO){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clienteDTO.getId()).toUri();
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
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

    @GetMapping(value = "/filter/list/name/cpfCnpj/cidade/uf/{nome}-{cpfCnpj}-{cidade}-{uf}")
    public ResponseEntity<List<Cliente>> findAllByNCCU(@PathVariable String nome, @PathVariable String cpfCnpj,
                                                       @PathVariable String cidade, @PathVariable String uf){
        return ResponseEntity.ok().body(service.findAllByNCCU(nome, cpfCnpj, cidade, uf));
    }

    @GetMapping(value = "/filter/list/cpfCnpj/cidade/{nome}-{cpfCnpj}-{cidade}")
    public ResponseEntity<List<Cliente>> findByNomeAndCpfCnpjAndCidade(@PathVariable String nome,
                                                                    @PathVariable String cpfCnpj,
                                                                    @PathVariable String cidade){
        return ResponseEntity.ok().body(service.findByNomeAndCpfCnpjAndCidade(nome, cpfCnpj, cidade));
    }

    @GetMapping(value = "/filter/list/cpfCnpj/uf/{nome}-{cpfCnpj}-{uf}")
    public ResponseEntity<List<Cliente>> findByNomeAndCpfCnpjAndUf(@PathVariable String nome,
                                                                    @PathVariable String cpfCnpj,
                                                                    @PathVariable String uf){
        return ResponseEntity.ok().body(service.findByNomeAndCpfCnpjAndUf(nome, cpfCnpj, uf));
    }

    @GetMapping(value = "/filter/list/name/cidade/uf/{nome}-{cidade}-{uf}")
    public ResponseEntity<List<Cliente>> findByNomeAndCidadeAndUf(@PathVariable String nome,
                                                                  @PathVariable String cidade,
                                                                  @PathVariable String uf){
        return ResponseEntity.ok().body(service.findByNomeAndCidadeAndUf(nome, cidade, uf));
    }

    @GetMapping(value = "/filter/list/name/cpfCnpj/{nome}-{cpfCnpj}")
    public ResponseEntity<List<Cliente>> findByNomeAndCpfCnpj(@PathVariable String nome,@PathVariable String cpfCnpj){
        return ResponseEntity.ok().body(service.findByNomeAndCpfCnpj(nome, cpfCnpj));
    }

    @GetMapping(value = "/filter/list/name/cidade/{nome}-{cidade}")
    public ResponseEntity<List<Cliente>> findByNomeAndCidade(@PathVariable String nome,@PathVariable String cidade){
        return ResponseEntity.ok().body(service.findByNomeAndCidade(nome, cidade));
    }

    @GetMapping(value = "/filter/list/name/uf/{nome}-{uf}")
    public ResponseEntity<List<Cliente>> findByNomeAndUf(String nome, String uf){
        return ResponseEntity.ok().body(service.findByNomeAndUf(nome, uf));
    }

    @GetMapping(value = "/filter/list/cpfCnpj/cidade/{cpfCnpj}-{cidade}")
    public ResponseEntity<List<Cliente>> findByCpfCnpjAndCidade(@PathVariable String cpfCnpj,@PathVariable String cidade) {
            return ResponseEntity.ok().body(service.findByCpfCnpjAndCidade(cpfCnpj, cidade));
    }

    @GetMapping(value = "/filter/list/cpfCnpj/uf/{cpfCnpj}-{uf}")
    public ResponseEntity<List<Cliente>> findByCpfCnpjAndUf(@PathVariable String cpfCnpj,@PathVariable String uf) {
        return ResponseEntity.ok().body(service.findByCpfCnpjAndUf(cpfCnpj, uf));
    }

    @GetMapping(value = "/filter/list/cidade/uf/{cidade}-{uf}")
    public ResponseEntity<List<Cliente>> findByCidadeAndUf(String cidade, String uf) {
        return ResponseEntity.ok().body(service.findByCpfCnpjAndUf(cidade, uf));
    }

    @GetMapping(value = "/filter/list/cpfCnpj/{cpfCnpj}")
    public ResponseEntity<List<Cliente>> findByCpfCnpj(@PathVariable String cpfCnpj){
        return ResponseEntity.ok().body(service.findByCpfCnpj(cpfCnpj));
    }

    @GetMapping(value = "/filter/list/cidade/{cidade}")
    public ResponseEntity<List<Cliente>> findByCidade(@PathVariable String cidade){
        return ResponseEntity.ok().body(service.findByCidade(cidade));
    }

    @GetMapping(value = "filter/list/cidade/{uf}")
    public ResponseEntity<List<Cliente>> findByUf(@PathVariable String uf){
        return ResponseEntity.ok().body(service.findByUf(uf));
    }
}
