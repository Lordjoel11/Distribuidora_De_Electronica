package com.Districto_Tech.distribuidora.features.employees;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.common.MapperConfig;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeRequestDTO;
import com.Districto_Tech.distribuidora.features.employees.dtos.EmployeeResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeModelMapper implements IModelMapper<EmployeeEntity, EmployeeResponseDTO, EmployeeRequestDTO> {

    private MapperConfig mapperConfig;

    @Override
    public EmployeeEntity toEntity(EmployeeRequestDTO employeeRequestDTO) {
        return mapperConfig.modelMapper().map(employeeRequestDTO, EmployeeEntity.class);
    }

    @Override
    public EmployeeResponseDTO toDto(EmployeeEntity employeeEntity) {
        return mapperConfig.modelMapper().map(employeeEntity, EmployeeResponseDTO.class);
    }
}
