package com.mtzz.services;

import com.mtzz.entities.Cliente;
import com.mtzz.repositories.ClienteRepository;
import com.mtzz.services.exceptions.ValueNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class ClienteService {

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
        cliente.setDataCadastro(user.dateFormat());
        cliente.setNome(user.nameFormat(cliente.getNome()));
        cliente.setCpfCnpj(numberFormat(cliente.getCpfCnpj()));
        cliente.setLogradouro(user.nameFormat(cliente.getLogradouro()));
        cliente.setCidade(user.nameFormat(cliente.getCidade()));
        cliente.setUf(user.nameFormat(cliente.getUf()));
        cliente.setTelefone(numberFormat(cliente.getTelefone()));
        return repository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente){
        Cliente newCliente = repository.getReferenceById(id);
        newCliente = updateData(newCliente, cliente);
        return repository.save(cliente);
    }
    
    public void deleteCliente(Long id){
        Cliente client = findById(id);
        if(client != null){
            repository.deleteById(id);
        }


    }

    public Cliente updateData(Cliente dataClient, Cliente newDataClient){
        try {
            dataClient.setLogradouro(newDataClient.getLogradouro());
            dataClient.setCidade(newDataClient.getCidade());
            dataClient.setUf(newDataClient.getUf());
            dataClient.setCep(newDataClient.getCep());
            //alterações opcionais(dados não alterados, permanecem nulos(vazios))
            dataClient.setTelefone(newDataClient.getTelefone());
            dataClient.setEmail(newDataClient.getEmail());

        }catch (NullPointerException err){
            throw new ValueNotFoundException("Change values referring to street, city, uf, zip code, telephone and " +
                    "email must be parameterized");
        } finally {
          return dataClient;
        }

    }

    protected String numberFormat(String number) {
        return number = number.replaceAll("[^0-9]+", "");
    }
}
