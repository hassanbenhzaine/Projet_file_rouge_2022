package com.youcode.crm.mapper;

import com.youcode.crm.entity.SellingInvoice;
import com.youcode.crm.entity.dto.SellingInvoiceDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SellingInvoiceMapper {

    @Mappings({
            @Mapping(target = "customerId", source = "customer.id"),
            @Mapping(target = "customerFirstName", source = "customer.firstName"),
            @Mapping(target = "customerLastName", source = "customer.lastName")
    })
    SellingInvoiceDTO mapSellingInvoiceToDTO(SellingInvoice sellingInvoice);

    @InheritInverseConfiguration
    SellingInvoice mapSellingInvoiceDtoToSellingInvoice(SellingInvoiceDTO sellingInvoiceDTO);
}
