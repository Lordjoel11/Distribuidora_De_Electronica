package com.Districto_Tech.distribuidora.features.employees;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.EmployeeAlreadyExistsException;
import com.Districto_Tech.distribuidora.common.exceptions.EmployeeNotFoundException;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeRequestDTO;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class EmployeeService implements IService<EmployeeRequestDTO, EmployeeResponseDTO, Long> {

    private EmployeeRepository employeeRepository;
    private EmployeeModelMapper employeeModelMapper;

    @Override
    public EmployeeResponseDTO save(EmployeeRequestDTO request) {

        if(employeeRepository.existsByCUIL(request.getCUIL())){
            throw new EmployeeAlreadyExistsException("The employee already exists");
        }

        EmployeeEntity employee=employeeModelMapper.toEntity(request);
        employeeRepository.save(employee);

        return employeeModelMapper.toDto(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getAll() {
        return employeeRepository.findAll().stream().map(employeeModelMapper::toDto).toList();
    }

    @Override
    public EmployeeResponseDTO getById(Long ID) {
        EmployeeEntity employee=employeeRepository.findById(ID).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employeeModelMapper.toDto(employee);
    }

    @Override
    public EmployeeResponseDTO update(Long ID, EmployeeRequestDTO request, Long phoneNumber) {
        if(!employeeRepository.existsById(ID)){
            throw new EmployeeNotFoundException("Employee not found, can't be updated");
        }
        EmployeeEntity updatedEmployee=employeeModelMapper.toEntity(request);
        updatedEmployee.setPhoneNumber(phoneNumber);
        employeeRepository.save(updatedEmployee);
        return employeeModelMapper.toDto(updatedEmployee);
    }

    @Override
    public void deleteById(Long ID) {
        if(!employeeRepository.existsById(ID)){
            throw new EmployeeNotFoundException("Employee not found, can't be deleted");
        }
        employeeRepository.deleteById(ID);
    }
}
