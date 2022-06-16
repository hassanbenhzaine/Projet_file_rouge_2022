package com.youcode.crm.service;

import com.youcode.crm.entity.PurchasePosition;
import com.youcode.crm.entity.dto.PurchasePositionDTO;
import com.youcode.crm.mapper.PurchasePositionMapper;
import com.youcode.crm.repository.PurchasePositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mateusz Milewczyk (agiklo)
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class PurchasePositionService {

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final PurchasePositionRepository purchasePositionRepository;
    private final PurchasePositionMapper purchasePositionMapper;

    /**
     * The method is to retrieve all purchases positions from the database and display them.
     *
     * After downloading all the data about the purchases positions,
     * the data is mapped to dto which will display only those needed
     * @return list of all purchases positions with specification of data in PurchasesPositionsToDTO
     */
    @Transactional(readOnly = true)
    public List<PurchasePositionDTO> getAllPurchasesPositions(Pageable pageable){
        return purchasePositionRepository.findAll(pageable)
                .stream()
                .map(purchasePositionMapper::mapPurchasePositionToDTO)
                .collect(Collectors.toList());
    }

    /**
     * The method is to download a specific purchases position from the database and display it.
     * After downloading all the data about the purchases position,
     * the data is mapped to dto which will display only those needed
     *
     * @param id id of the purchases position to be searched for
     * @throws ResponseStatusException if the id of the purchases position you are looking for does not exist throws 404 status
     * @return detailed data about a specific purchases position
     */
    @Transactional(readOnly = true)
    public PurchasePositionDTO getpurchasePositiontById(Long id) {
        PurchasePosition purchasePosition = purchasePositionRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase position cannot be found, the specified id does not exist"));
        return purchasePositionMapper.mapPurchasePositionToDTO(purchasePosition);
    }
}
