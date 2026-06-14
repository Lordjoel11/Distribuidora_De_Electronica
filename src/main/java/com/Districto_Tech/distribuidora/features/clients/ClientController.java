package com.Districto_Tech.distribuidora.features.clients;


import com.Districto_Tech.distribuidora.features.clients.dto.ClientRequestDTO;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientRequestDTO));

    }


}
