package com.mtzz.services.exceptions;

public class RegisterNotFoundException extends RuntimeException{

    public RegisterNotFoundException(String msg){
        super(msg);
    }
}
