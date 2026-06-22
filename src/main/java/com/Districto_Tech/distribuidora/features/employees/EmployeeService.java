package com.Districto_Tech.distribuidora.features.employees;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.EmployeeAlreadyExistsException;
import com.Districto_Tech.distribuidora.common.exceptions.EmployeeNotFoundException;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeRequestDTO;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeResponseDTO;
import com.Districto_Tech.distribuidora.features.users.UserEntity;
import com.Districto_Tech.distribuidora.features.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService implements IService<EmployeeRequestDTO, EmployeeResponseDTO, Long> {

    private EmployeeRepository employeeRepository;
    private EmployeeModelMapper employeeModelMapper;
    private UserRepository userRepository;

    @Override
    public EmployeeResponseDTO save(EmployeeRequestDTO request) {

        if (employeeRepository.existsByCUIL(request.getCUIL())) {
            throw new EmployeeAlreadyExistsException("Ya existe un empleado con ese CUIL.");
        }

        Optional<EmployeeEntity> existing = employeeRepository.findByUser_Id(request.getUserId());
        if (existing.isPresent()) {
            throw new IllegalStateException("Este usuario ya tiene un perfil de empleado.");
        }

        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + request.getUserId()));

        EmployeeEntity employee = EmployeeEntity.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .CUIL(request.getCUIL())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .user(user)
                .build();

        employee = employeeRepository.save(employee);

        return employeeModelMapper.toDto(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getAll() {
        return employeeRepository.findAll().stream().map(employeeModelMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO getById(Long ID) {
        EmployeeEntity employee = employeeRepository.findById(ID)
                .orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado."));
        return employeeModelMapper.toDto(employee);
    }

    @Override
    public EmployeeResponseDTO update(Long ID, EmployeeRequestDTO request) {
        EmployeeEntity existing = employeeRepository.findById(ID)
                .orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado."));

        existing.setName(request.getName());
        existing.setSurname(request.getSurname());
        existing.setCUIL(request.getCUIL());
        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setRole(request.getRole());

        employeeRepository.save(existing);
        return employeeModelMapper.toDto(existing);
    }

    @Override
    public void deleteById(Long ID) {
        if (!employeeRepository.existsById(ID)) {
            throw new EmployeeNotFoundException("Empleado no encontrado.");
        }
        employeeRepository.deleteById(ID);
    }
}