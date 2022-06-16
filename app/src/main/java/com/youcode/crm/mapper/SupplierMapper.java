package com.youcode.crm.mapper;

import com.youcode.crm.entity.Supplier;
import com.youcode.crm.entity.dto.SupplierDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    @Mappings({
        @Mapping(target = "transportTypeFullName", source = "modeOfTransportCode.fullName"),
        @Mapping(target = "transportTypeId", source = "modeOfTransportCode.code")
    })
    SupplierDTO mapSupplierToDTO(Supplier supplier);

    @InheritInverseConfiguration
    Supplier mapSupplierDtoToSupplier(SupplierDTO supplierDTO);
}
