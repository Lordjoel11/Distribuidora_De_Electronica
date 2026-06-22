package com.Districto_Tech.distribuidora.features.clients;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientRequestDTO;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clients", description = "Gestión de clientes (solo Admin)")
@RestController
@RequestMapping("/api/v1/admin/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Crear cliente", description = "Crea perfil de cliente")
    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientRequestDTO request) {
        ClientResponseDTO newUser = clientService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @Operation(summary = "Listar todos los clientes")
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @Operation(summary = "Buscar Cliente por Id")
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        ClientResponseDTO user = clientService.getById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Actualizar cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.update(id, request));
    }

    @Operation(summary = "Eliminar Cliente por Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
