package br.com.getfinance.exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException(String message){
        super(message);
    }
}
