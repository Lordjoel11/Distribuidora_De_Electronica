package com.Districto_Tech.distribuidora.features.employees;


import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeRequestDTO;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
        return ResponseEntity.ok().body(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok().body(employeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(@Valid @RequestBody EmployeeRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO request){
        return ResponseEntity.ok().body(employeeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
