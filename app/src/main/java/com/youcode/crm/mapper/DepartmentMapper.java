package com.youcode.crm.mapper;

import com.youcode.crm.entity.Department;
import com.youcode.crm.entity.dto.DepartmentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

   @Mappings({
           @Mapping(target = "id", source = "id"),
           @Mapping(target = "name", source = "name"),
           @Mapping(target = "city", source = "city")
   })
   DepartmentDTO mapDepartmentToDto(Department department);

   @InheritInverseConfiguration
   Department mapDepartmentDtoToDepartment(DepartmentDTO departmentDTO);

}
