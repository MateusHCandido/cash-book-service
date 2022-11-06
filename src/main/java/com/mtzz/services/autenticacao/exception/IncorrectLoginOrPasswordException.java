package com.mtzz.services.autenticacao.exception;

public class IncorrectLoginOrPasswordException extends RuntimeException{

    public IncorrectLoginOrPasswordException(String msg){
        super(msg);
    }
}
