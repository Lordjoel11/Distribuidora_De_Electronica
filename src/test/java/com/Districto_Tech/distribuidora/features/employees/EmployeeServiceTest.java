package com.Districto_Tech.distribuidora.features.employees;

import com.Districto_Tech.distribuidora.common.exceptions.EmployeeAlreadyExistsException;
import com.Districto_Tech.distribuidora.common.exceptions.EmployeeNotFoundException;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeRequestDTO;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeResponseDTO;
import com.Districto_Tech.distribuidora.features.employees.misc.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeModelMapper employeeModelMapper;

    @InjectMocks
    private EmployeeService employeeService;

    private EmployeeEntity employeeEntity;
    private EmployeeRequestDTO requestDTO;
    private EmployeeResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        employeeEntity = EmployeeEntity.builder()
                .idEmployee(1L)
                .name("Laura")
                .surname("Rodríguez")
                .CUIL("20-12345678-9")
                .phoneNumber("1155667788")
                .role(Role.VENDOR)
                .build();

        requestDTO = EmployeeRequestDTO.builder()
                .name("Laura")
                .surname("Rodríguez")
                .CUIL("20-12345678-9")
                .role(Role.VENDOR)
                .build();

        responseDTO = EmployeeResponseDTO.builder()
                .name("Laura")
                .surname("Rodríguez")
                .CUIL("20-12345678-9")
                .role(Role.VENDOR)
                .build();
    }

    @Test
    void save_ShouldCreateEmployeeSuccessfully() {
        when(employeeRepository.existsByCUIL(requestDTO.getCUIL())).thenReturn(false);
        when(employeeModelMapper.toEntity(any(EmployeeRequestDTO.class))).thenReturn(employeeEntity);
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);
        when(employeeModelMapper.toDto(any(EmployeeEntity.class))).thenReturn(responseDTO);

        EmployeeResponseDTO result = employeeService.save(requestDTO);

        assertNotNull(result);
        assertEquals("Laura", result.getName());
        assertEquals(Role.VENDOR, result.getRole());
        verify(employeeRepository).save(any());
    }

    @Test
    void save_WhenCUILExists_ShouldThrowEmployeeAlreadyExistsException() {
        when(employeeRepository.existsByCUIL(requestDTO.getCUIL())).thenReturn(true);

        assertThrows(EmployeeAlreadyExistsException.class, () -> employeeService.save(requestDTO));
    }

    @Test
    void getAll_ShouldReturnAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(employeeEntity));
        when(employeeModelMapper.toDto(any(EmployeeEntity.class))).thenReturn(responseDTO);

        List<EmployeeResponseDTO> result = employeeService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getById_WhenEmployeeExists_ShouldReturnEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity));
        when(employeeModelMapper.toDto(any(EmployeeEntity.class))).thenReturn(responseDTO);

        EmployeeResponseDTO result = employeeService.getById(1L);

        assertNotNull(result);
        assertEquals("Laura", result.getName());
    }

    @Test
    void getById_WhenEmployeeNotExists_ShouldThrowException() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getById(99L));
    }

    @Test
    void update_ShouldUpdateEmployeeSuccessfully() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        when(employeeModelMapper.toEntity(any(EmployeeRequestDTO.class))).thenReturn(employeeEntity);
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);
        when(employeeModelMapper.toDto(any(EmployeeEntity.class))).thenReturn(responseDTO);

        EmployeeResponseDTO result = employeeService.update(1L, requestDTO);

        assertNotNull(result);
        verify(employeeRepository).save(any());
    }

    @Test
    void update_WhenEmployeeNotExists_ShouldThrowException() {
        when(employeeRepository.existsById(99L)).thenReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.update(99L, requestDTO));
    }

    @Test
    void deleteById_WhenEmployeeExists_ShouldDeleteSuccessfully() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);

        assertDoesNotThrow(() -> employeeService.deleteById(1L));

        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void deleteById_WhenEmployeeNotExists_ShouldThrowException() {
        when(employeeRepository.existsById(99L)).thenReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteById(99L));
    }
}