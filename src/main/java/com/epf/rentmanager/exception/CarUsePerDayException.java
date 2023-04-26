package com.epf.rentmanager.exception;

public class CarUsePerDayException extends Exception{
    public CarUsePerDayException(){
        super();
    }

    public CarUsePerDayException(String message){
        super(message);
    }
}
