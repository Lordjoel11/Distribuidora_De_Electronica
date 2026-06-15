package com.Districto_Tech.distribuidora.features.shipping;


import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeRequestDTO;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeResponseDTO;
import com.Districto_Tech.distribuidora.features.shipping.dtos.ShippingRequestDTO;
import com.Districto_Tech.distribuidora.features.shipping.dtos.ShippingResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private ShippingService shippingService;

    @GetMapping
    public ResponseEntity<List<ShippingResponseDTO>> getShipments(){
        return ResponseEntity.ok().body(shippingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingResponseDTO> getShipmentsById(@PathVariable UUID id){
        return ResponseEntity.ok().body(shippingService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ShippingResponseDTO> saveShipment(@Valid @RequestBody ShippingRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(shippingService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingResponseDTO> updateShipment(@PathVariable UUID id, @Valid @RequestBody ShippingRequestDTO request){
        return ResponseEntity.ok().body(shippingService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable UUID id){
        shippingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
