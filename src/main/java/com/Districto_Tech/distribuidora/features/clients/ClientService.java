package com.Districto_Tech.distribuidora.features.clients;

import com.Districto_Tech.distribuidora.features.clients.dto.ClientRequestDTO;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private ClientMapper clientMapper;
    private ClientRepository clientRepository;

    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {

        if(clientRepository.findByDNI(clientRequestDTO.DNI()) == null){
            ClientEntity clientEntity = clientMapper.toEntity(clientRequestDTO);
            return clientMapper.toDto(clientRepository.save(clientEntity));
        }else{

            throw new RuntimeException()

        }

    }



}
