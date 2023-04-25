package com.epf.rentmanager.exception;

public class MailUseException extends Exception{
    public MailUseException(){
        super();
    }

    public MailUseException(String message){
        super(message);
    }
}
