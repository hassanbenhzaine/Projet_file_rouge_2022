package com.youcode.crm.mapper;

import com.youcode.crm.entity.Product;
import com.youcode.crm.entity.dto.ProductDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "typeId", source = "type.id"),
            @Mapping(target = "typeFullName", source = "type.fullName")
    })
    ProductDTO mapProductToDto(Product product);

    @InheritInverseConfiguration
    Product mapProductDtoToProduct(ProductDTO productDTO);
}
