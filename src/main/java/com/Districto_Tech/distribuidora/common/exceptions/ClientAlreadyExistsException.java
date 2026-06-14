package com.Districto_Tech.distribuidora.common.exceptions;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String message) {
        super("There seems to be an error: "+message);
    }
}
