package com.mtzz.services.exceptions;

public class ExistingUserLoginExcepetion extends RuntimeException{

    public ExistingUserLoginExcepetion(String msg){
        super(msg);
    }
}
