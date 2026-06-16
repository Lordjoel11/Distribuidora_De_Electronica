package com.Districto_Tech.distribuidora.common.exceptions;

public class ShippingNotFoundException extends RuntimeException {
    public ShippingNotFoundException(String message) {
        super("An error has occurred: "+message);
    }
}
