package com.panvdev.apirest_prueba.excepciones;

public class ItemAlreadyExists extends RuntimeException {
    public ItemAlreadyExists(String message) {
        super(message);
    }
}