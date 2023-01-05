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
            cliente.setDataCadastro(dateAndTimeFormat());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cliente.setNome(nameFormat(cliente.getNome()));
        cliente.setCpfCnpj(numberFormat(cliente.getCpfCnpj()));
        cliente.setLogradouro(nameFormat(cliente.getLogradouro()));
        cliente.setCidade(nameFormat(cliente.getCidade()));
        cliente.setUf(nameFormat(cliente.getUf()));
        cliente.setTelefone(numberFormat(cliente.getTelefone()));
        cliente.setEmail(nameFormat(cliente.getEmail()));
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

    public List<Cliente> findAllByNCCU(String nome, String cpfCnpj, String cidade, String uf){
        return repository.findByNomeAndCpfCnpjAndCidadeAndUf(nome,cpfCnpj,cidade,uf);
    }

    public List<Cliente> findByNomeAndCpfCnpjAndCidade(String nome, String cpfCnpj, String cidade){
        return repository.findByNomeAndCpfCnpjAndCidade(nome, cpfCnpj, cidade);
    }

    public List<Cliente> findByNomeAndCpfCnpjAndUf(String nome,String cpfCnpj, String uf){
        return repository.findByNomeAndCpfCnpjAndUf(nome, cpfCnpj, uf);
    }

    public List<Cliente> findByNomeAndCidadeAndUf(String nome, String cidade, String uf){
        return repository.findByNomeAndCidadeAndUf(nome, cidade, uf);
    }

    public List<Cliente> findByNomeAndCpfCnpj(String nome, String cpfCnpj){
        return repository.findByNomeAndCpfCnpj(nome, cpfCnpj);
    }

    public List<Cliente> findByNomeAndCidade(String nome, String cidade){
        return repository.findByNomeAndCidade(nome, cidade);
    }

    public List<Cliente> findByNomeAndUf(String nome, String uf){
        return repository.findByNomeAndUf(nome, uf);
    }

    public List<Cliente> findByCpfCnpjAndCidade(String cpfCnpj, String cidade){
        return repository.findByCpfCnpjAndCidade(cpfCnpj, cidade);
    }

    public List<Cliente> findByCpfCnpjAndUf(String cpfCnpj, String uf){
        return repository.findByCpfCnpjAndUf(cpfCnpj, uf);
    }

    public List<Cliente> findByCidadeAndUf(String cidade, String uf){
        return repository.findByCidadeAndUf(cidade, uf);
    }

    public List<Cliente> findByCpfCnpj(String cpfCnpj){
        return repository.findByCpfCnpj(cpfCnpj);
    }

    public List<Cliente> findByCidade(String cidade){
        return repository.findByCidade(cidade);
    }

    public List<Cliente> findByUf(String uf){
        return repository.findByUf(uf);
    }

}




