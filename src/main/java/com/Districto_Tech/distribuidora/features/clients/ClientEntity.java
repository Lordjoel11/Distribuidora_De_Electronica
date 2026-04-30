package com.Districto_Tech.distribuidora.features.clients;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

@AllArgsConstructor
@Getter
@ToString
@Builder
@Setter
public class ClientEntity {

    @Setter(AccessLevel.NONE)
    private int clientID;
    private String name;
    private String surname;
    @Setter(AccessLevel.NONE)
    private String DNI;
    private ClientType clientType;
    private String phoneNumber;
    private Adress homeAdress;
    @Setter(AccessLevel.NONE)
    private int userID;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return clientID == that.clientID && userID == that.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientID, userID);
    }
}
