package com.Districto_Tech.distribuidora.features.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@Getter
@ToString
public class ClientEntity {

    private int clientID;
    private String name;
    private String surname;
    private String DNI;
    private ClientType clientType;
    private String phoneNumber;
    private Adress homeAdress;
    private int userID;


}
