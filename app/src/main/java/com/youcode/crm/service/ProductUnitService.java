package com.youcode.crm.service;

import com.youcode.crm.entity.ProductUnit;
import com.youcode.crm.entity.dto.ProductUnitDTO;
import com.youcode.crm.mapper.ProductUnitMapper;
import com.youcode.crm.repository.ProductUnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mateusz Milewczyk (agiklo)
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class ProductUnitService {

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final ProductUnitRepository productUnitRepository;
    private final ProductUnitMapper productUnitMapper;

    /**
     * The method is to retrieve all ProductUnits from the database and display them.
     *
     * After downloading all the data about the ProductUnits,
     * the data is mapped to dto which will display only those needed
     * @return list of all ProductUnits with specification of data in ProductUnitsDTO
     */
    @Transactional(readOnly = true)
    public List<ProductUnitDTO> getAllProductUnits(Pageable pageable){
        return productUnitRepository.findAll(pageable)
                .stream()
                .map(productUnitMapper::mapProductUnitToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductUnit> addNewProductUnits(Iterable<ProductUnit> productUnits) {
        return productUnitRepository.saveAll(productUnits);
    }
}
