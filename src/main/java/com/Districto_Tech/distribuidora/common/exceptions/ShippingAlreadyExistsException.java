package com.Districto_Tech.distribuidora.common.exceptions;

public class ShippingAlreadyExistsException extends RuntimeException {
    public ShippingAlreadyExistsException(String message) {
        super("An error has occurred: "+message);
    }
}
