package com.Districto_Tech.distribuidora.common.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super("An error has ocurred: "+message);
    }
}
