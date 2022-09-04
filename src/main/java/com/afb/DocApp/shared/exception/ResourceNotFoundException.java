package com.afb.DocApp.shared.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String entity, Long id) {
        super(String.format("%s con id %d no existe.", entity, id));
    }
    public ResourceNotFoundException(){
        super("El usuario no existe / Credenciales invalidas.");
    }
}
