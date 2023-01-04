package com.example.boot.exceptions;

public class ModelNotFound extends RuntimeException {
    public ModelNotFound(String message){
        super(message);
    }
    
}
