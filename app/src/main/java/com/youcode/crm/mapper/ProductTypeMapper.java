package com.youcode.crm.mapper;

import com.youcode.crm.entity.ProductType;
import com.youcode.crm.entity.dto.ProductTypeDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {

    @Mappings({
            @Mapping(target = "discount", source = "discount"),
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "fullName", source = "fullName"),
            @Mapping(target = "periodOfAvailability", source = "periodOfAvailability")
    })
    ProductTypeDTO mapProductTypeToDTO(ProductType productType);

    @InheritInverseConfiguration
    ProductType mapProductTypeDtoToProductType(ProductTypeDTO productTypeDTO);
}
