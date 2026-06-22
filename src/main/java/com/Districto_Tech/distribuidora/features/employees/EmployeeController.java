package com.Districto_Tech.distribuidora.features.employees;


import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeRequestDTO;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Employees", description = "Gestión de empleados")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Listar todos los empleados")
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
        return ResponseEntity.ok().body(employeeService.getAll());
    }

    @Operation(summary = "Obtener empleado por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok().body(employeeService.getById(id));
    }

    @Operation(summary = "Crear empleado")
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(@Valid @RequestBody EmployeeRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(request));
    }

    @Operation(summary = "Actualizar empleado")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO request){
        return ResponseEntity.ok().body(employeeService.update(id, request));
    }

    @Operation(summary = "Eliminar empleado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
