package com.Districto_Tech.distribuidora.features.clients;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String message) {
        super("There seems to be an error: "+message);
    }
}
