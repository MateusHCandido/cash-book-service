package com.mtzz.services;

import com.mtzz.entities.Cliente;
import com.mtzz.repositories.ClienteRepository;
import com.mtzz.services.exceptions.ValueNotFoundException;
import com.mtzz.services.util.FormatData;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.*;


@Service
public class ClienteService extends FormatData {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private UsuarioService user;


    public Cliente findById(Long id){
        Optional<Cliente> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException
                ("Client not found! Id: " + id, "Cause type: " + Cliente.class.getName()));
    }

    public Cliente createCliente(Cliente cliente){
        try {
            cliente.setDataCadastro(dateFormat());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cliente.setNome(nameFormat(cliente.getNome()));
        cliente.setCpfCnpj(numberFormat(cliente.getCpfCnpj()));
        cliente.setLogradouro(nameFormat(cliente.getLogradouro()));
        cliente.setCidade(nameFormat(cliente.getCidade()));
        cliente.setUf(nameFormat(cliente.getUf()));
        cliente.setTelefone(numberFormat(cliente.getTelefone()));
        return repository.save(cliente);
    }

    public void updateCliente(Cliente cliente){
        Cliente newCliente = repository.getReferenceById(cliente.getId());
        newCliente = updateData(newCliente, cliente);
        repository.save(newCliente);
    }
    
    public void deleteCliente(Long id){
        Cliente client = findById(id);
        if(client != null){
            repository.deleteById(id);
        }


    }

    public Cliente updateData(Cliente dataClient, Cliente newDataClient){
        try {
            dataClient.setLogradouro(nameFormat(newDataClient.getLogradouro()));
            dataClient.setCidade(nameFormat(newDataClient.getCidade()));
            dataClient.setUf(nameFormat(newDataClient.getUf()));
            dataClient.setCep(nameFormat(newDataClient.getCep()));

            //alterações opcionais(dados não alterados, permanecem nulos(vazios))
            if(newDataClient.getTelefone() != null) {
                dataClient.setTelefone(nameFormat(newDataClient.getTelefone()));
            }else {dataClient.setTelefone("");}

            if(newDataClient.getEmail() != null){
                dataClient.setEmail(nameFormat(newDataClient.getEmail()));
            }else {dataClient.setEmail("");}

        }catch (NullPointerException err){
            throw new ValueNotFoundException("Change values referring to street, city, uf, zip code, telephone and " +
                    "email must be parameterized");
        }

        return dataClient;
    }

    //FindByNameCpfCnpjpCidadeUF*
    public List<Map<String, String>> findAllByNCCU(){
        List<Cliente> allClients = repository.findAll();
        List<Map<String, String>> clients = new ArrayList<>();
        for(Cliente c: allClients){
            Map<String, String> clts = new HashMap<>();
            clts.put("Nome", c.getNome());
            clts.put("CPF_CNPJ", c.getCpfCnpj());
            clts.put("Cidade", c.getCidade());
            clts.put("UF", c.getUf());
            clients.add(clts);
        }
        return clients;
    }

    public List<Map<String, String>> findAllByName(){
        List<Map<String, String>> clients = findAllByNCCU();
        for(Map<String, String> c: clients){
            c.remove("CPF_CNPJ");
            c.remove("Cidade");
            c.remove("UF");
        }
        return clients;
    }

    public List<Map<String, String>> findAllByCpfCnpj(){
        List<Map<String, String>> clients = findAllByNCCU();
        for(Map<String, String> c: clients){
            c.remove("Nome");
            c.remove("Cidade");
            c.remove("UF");
        }
        return clients;
    }

    public List<Map<String, String>> findAllByCidade(){
        List<Map<String, String>> clients = findAllByNCCU();
        for(Map<String, String> c: clients){
            c.remove("Nome");
            c.remove("CPF_CNPJ");
            c.remove("UF");
        }
        return clients;
    }

    public List<Map<String, String>> findAllByUF(){
        List<Map<String, String>> clients = findAllByNCCU();
        for(Map<String, String> c: clients){
            c.remove("Nome");
            c.remove("CPF_CNPJ");
            c.remove("Cidade");
        }
        return clients;
    }


}




