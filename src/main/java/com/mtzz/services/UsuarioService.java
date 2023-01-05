package com.mtzz.services;

import com.mtzz.entities.Usuario;
import com.mtzz.repositories.UserRepository;
import com.mtzz.services.autenticacao.ValidacaoUsuario;
import com.mtzz.services.autenticacao.exception.InativeUserException;
import com.mtzz.services.autenticacao.exception.IncorrectLoginOrPasswordException;
import com.mtzz.services.exceptions.DatabaseException;
import com.mtzz.services.exceptions.ExistingUserLoginExcepetion;
import com.mtzz.services.util.FormatData;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;


@Service
public class UsuarioService extends FormatData implements ValidacaoUsuario {

    @Autowired
    private UserRepository repository;


    @Override
    public Usuario autenticarLogin(String login, String senha) {
        Usuario user = repository.findByLoginAndSenha(login, senha);
        if(user == null){
            throw new IncorrectLoginOrPasswordException("Login or password invalid");
        }
        if (user.getLogin().equals(login) && (user.getSenha().equals(senha))) {
            if (user.getStatus().equals("A")) {
                user = repository.getReferenceById(user.getId());
            } else if(user.getStatus().equals("C")){
                throw new InativeUserException("User is not active");
            }
        }
        return user;
    }

    public Usuario create(Usuario usuario){
        try {
            usuario.setNome(nameFormat(usuario.getNome()));
            usuario.setTelefone(numberFormat(usuario.getTelefone()));
            try {
                usuario.setDataCadastro(dateAndTimeFormat());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (ConstraintViolationException error){
            throw new ExistingUserLoginExcepetion(error.getMessage());
        }
        return repository.save(usuario);
    }

    public Usuario findById(Long id){
        Optional<Usuario> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException
                ("User not found! Id: " + id, "Cause type: " + Usuario.class.getName()));
    }

    public void update(Usuario usuario){
        Usuario newUser = repository.getReferenceById(usuario.getId());
        newUser = updateData(newUser, usuario);
        repository.save(newUser);
    }

    public void delete(Long id){
        try{
            repository.delete(findById(id));
        }catch (EmptyResultDataAccessException error01){
            throw new ObjectNotFoundException("User not found! Id: " + id, "Cause type: " + Usuario.class.getName());
        }catch (DataIntegrityViolationException error02){
            throw new DatabaseException(error02.getMessage());
        }
    }

    //Listar usuários por nome e email
    public List<Usuario> findAllByNameAndEmail(String name, String email) {
        return repository.findByNomeAndEmail(name, email);
    }

    //Listar usuários por nome
    public List<Usuario> findAllByName(String name){
        return repository.findByNome(name);
    }

    //Listar usuários por email
    public List<Usuario> findAllByEmail(String email){
        return repository.findByEmail(email);
    }

    public Usuario updateData(Usuario oldData, Usuario usuario) {
            //novos dados
            oldData.setNome(nameFormat(usuario.getNome()));
            oldData.setEmail(usuario.getEmail());
            oldData.setTelefone(usuario.getTelefone());
            oldData.setStatus(nameFormat(usuario.getStatus()));
        return oldData;
    }

}
