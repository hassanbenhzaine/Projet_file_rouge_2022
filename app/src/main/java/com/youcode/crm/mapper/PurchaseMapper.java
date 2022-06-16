package com.youcode.crm.mapper;

import com.youcode.crm.entity.Purchase;
import com.youcode.crm.entity.dto.PurchaseDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

   @Mappings({
           @Mapping(target = "customerId", source = "customer.id"),
           @Mapping(target = "customerFirstName", source = "customer.firstName"),
           @Mapping(target = "customerLastName", source = "customer.lastName"),
           @Mapping(target = "invoiceId", source = "invoice.id"),
   })
   PurchaseDTO mapPurchaseToDTO(Purchase purchase);

   @InheritInverseConfiguration
   Purchase mapPurchaseDtoToPurchase(PurchaseDTO purchaseDTO);
}
