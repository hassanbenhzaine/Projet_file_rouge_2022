package com.youcode.crm.mapper;

import com.youcode.crm.entity.PurchasePosition;
import com.youcode.crm.entity.dto.PurchasePositionDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchasePositionMapper {

    @Mappings({
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "productName", source = "product.name"),
            @Mapping(target = "purchaseId", source = "purchase.id"),
            @Mapping(target = "customerId", source = "purchase.customer.id"),
            @Mapping(target = "purchaseDate", source = "purchase.purchaseDate"),
            @Mapping(target = "sellingPrice", source = "product.sellingPrice")
    })
    PurchasePositionDTO mapPurchasePositionToDTO(PurchasePosition purchasePosition);

    @InheritInverseConfiguration
    PurchasePosition mapPurchasePositionDtoToPurchasePosition(PurchasePositionDTO purchasePositionDTO);
}