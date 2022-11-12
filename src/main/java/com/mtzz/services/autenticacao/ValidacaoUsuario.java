package com.mtzz.services.autenticacao;

import com.mtzz.entities.Usuario;

public interface ValidacaoUsuario {


    Usuario autenticarLogin(String login, String senha);
}
