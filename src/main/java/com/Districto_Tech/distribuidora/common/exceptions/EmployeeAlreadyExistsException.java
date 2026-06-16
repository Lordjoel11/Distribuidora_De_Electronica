package com.Districto_Tech.distribuidora.common.exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException(String message) {
        super("An error has occurred: " + message);
    }
}
