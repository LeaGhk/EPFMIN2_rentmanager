package com.epf.rentmanager.exception;

public class DebutIsBeforeFin extends Exception{
    public DebutIsBeforeFin(){
        super();
    }

    public DebutIsBeforeFin(String message){
        super(message);
    }
}
