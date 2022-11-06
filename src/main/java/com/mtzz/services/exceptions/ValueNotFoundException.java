package com.mtzz.services.exceptions;

public class ValueNotFoundException extends RuntimeException{

    public ValueNotFoundException(String msg){
        super (msg);
    }
}
