package com.mtzz.services;

import com.mtzz.entities.Cliente;
import com.mtzz.repositories.ClienteRepository;
import com.mtzz.services.exceptions.ValueNotFoundException;
import com.mtzz.services.util.FormatData;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.Optional;


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
        cliente.setNome(cliente.getNome());
        cliente.setCpfCnpj(cliente.getCpfCnpj());
        cliente.setLogradouro(cliente.getLogradouro());
        cliente.setCidade(cliente.getCidade());
        cliente.setUf(cliente.getUf());
        cliente.setTelefone(cliente.getTelefone());
        return repository.save(cliente);
    }



    public Cliente updateCliente(Cliente cliente){
        Cliente newCliente = repository.getReferenceById(cliente.getId());
        updateData(newCliente, cliente);
        return repository.save(cliente);
    }
    
    public void deleteCliente(Long id){
        Cliente client = findById(id);
        if(client != null){
            repository.deleteById(id);
        }


    }

    public void updateData(Cliente dataClient, Cliente newDataClient){
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
        }
    }
}




