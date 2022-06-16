package com.youcode.crm.service;

import com.youcode.crm.entity.ProductType;
import com.youcode.crm.entity.dto.ProductTypeDTO;
import com.youcode.crm.mapper.ProductTypeMapper;
import com.youcode.crm.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductTypeService {

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    /**
     * The method is to retrieve all ProductUnits from the database and display them.
     *
     * After downloading all the data about the ProductUnits,
     * the data is mapped to dto which will display only those needed
     * @return list of all ProductUnits with specification of data in ProductUnitsDTO
     */
    @Transactional(readOnly = true)
    public List<ProductTypeDTO> getAllProductTypes(Pageable pageable){
        return productTypeRepository.findAll(pageable)
                .stream()
                .map(productTypeMapper::mapProductTypeToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductType> addNewProductUnits(Iterable<ProductType> ProductTypes) {
        return productTypeRepository.saveAll(ProductTypes);
    }
}
