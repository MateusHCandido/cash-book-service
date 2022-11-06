package com.mtzz.services.autenticacao.exception;

public class InativeUserException extends RuntimeException{

    public InativeUserException(String msg){
        super(msg);
    }
}
