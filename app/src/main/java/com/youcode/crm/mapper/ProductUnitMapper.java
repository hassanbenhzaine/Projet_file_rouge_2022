package com.youcode.crm.mapper;

import com.youcode.crm.entity.ProductType;
import com.youcode.crm.entity.ProductUnit;
import com.youcode.crm.entity.dto.ProductTypeDTO;
import com.youcode.crm.entity.dto.ProductUnitDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductUnitMapper {

    @Mappings({
            @Mapping(target = "unitOfMeasure", source = "unitOfMeasure."),
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "productName", source = "product.name")
    })
    ProductUnitDTO mapProductUnitToDTO(ProductUnit productUnit);

    @InheritInverseConfiguration
    ProductUnit mapProductUnitDtoToProductUnit(ProductUnitDTO productUnitDTO);
}
