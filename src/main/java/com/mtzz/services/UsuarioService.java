package com.mtzz.services;

import com.mtzz.entities.Usuario;
import com.mtzz.repositories.DTO.userDTO.EmailDTO;
import com.mtzz.repositories.DTO.userDTO.NameDTO;
import com.mtzz.repositories.DTO.userDTO.UserDTO;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
            try {
                usuario.setDataCadastro(dateFormat());
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
        newUser = updateDate(newUser, usuario);
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

    public List<UserDTO> findAllByNameAndEmail() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<Usuario> users = repository.findAll();
        for (Usuario u: users) {
            userDTOS.add(new UserDTO(u.getId(), u.getNome(), u.getEmail()));
        }
        return userDTOS;
    }

    public List<NameDTO> findAllByName(){
        List<NameDTO> userDTOS = new ArrayList<>();
        List<Usuario> users = repository.findAll();
        for (Usuario u: users) userDTOS.add(new NameDTO(u.getNome()));
        return userDTOS;
    }

    public List<EmailDTO> findAllByEmail(){
        List<EmailDTO> usersDTO = new ArrayList<>();
        List<Usuario> users = repository.findAll();
        for (Usuario u: users) usersDTO.add(new EmailDTO(u.getEmail()));
        return usersDTO;
    }

    public Usuario updateDate(Usuario oldData, Usuario usuario) {
            //novos dados
            oldData.setNome(nameFormat(usuario.getNome()));
            oldData.setEmail(usuario.getEmail());
            oldData.setTelefone(usuario.getTelefone());
            oldData.setStatus(nameFormat(usuario.getStatus()));
        return oldData;
    }






}
