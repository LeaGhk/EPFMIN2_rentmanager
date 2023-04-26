package com.epf.rentmanager.exception;

public class UserIs18yearsException extends Exception{

    public UserIs18yearsException(){
        super();
    }

    public UserIs18yearsException(String message){
        super(message);
    }
}
