package com.youcode.crm.service;

import com.youcode.crm.entity.Purchase;
import com.youcode.crm.entity.dto.PurchaseDTO;
import com.youcode.crm.mapper.PurchaseMapper;
import com.youcode.crm.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class PurchaseService {

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    /**
     * The method is to retrieve all purchases from the database and display them.
     *
     * After downloading all the data about the purchase,
     * the data is mapped to dto which will display only those needed
     * @return list of all purchases with specification of data in PurchasesToDTO
     */
    @Transactional(readOnly = true)
    public List<PurchaseDTO> getAllPurchases(Pageable pageable){
        return purchaseRepository.findAll(pageable)
                .stream()
                .map(purchaseMapper::mapPurchaseToDTO)
                .collect(Collectors.toList());
    }

    /**
     * The method is to download a specific purchase from the database and display it.
     * After downloading all the data about the purchase,
     * the data is mapped to dto which will display only those needed
     *
     * @param id id of the purchase to be searched for
     * @throws ResponseStatusException if the id of the purchase you are looking for does not exist throws 404 status
     * @return detailed data about a specific purchase
     */
    @Transactional(readOnly = true)
    public PurchaseDTO getPurchaseById(Long id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase cannot be found, the specified id does not exist"));
        return purchaseMapper.mapPurchaseToDTO(purchase);
    }

    /**
     * The task of the method is to add a purchase to the database.
     * @param purchase requestbody of the purchase to be saved
     * @return saving the purchase to the database
     */
    public PurchaseDTO addNewPurchase(PurchaseDTO purchase) {
        Purchase addedPurchase = purchaseRepository.save(purchaseMapper.mapPurchaseDtoToPurchase(purchase));
        return purchaseMapper.mapPurchaseToDTO(addedPurchase);
    }

    /**
     * Method deletes the selected purchase by id
     * @param id id of the purchase to be deleted
     * @throws ResponseStatusException if id of the purchase is incorrect throws 404 status with message
     */
    public void deletePurchaseById(Long id) {
        try{
            purchaseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The specified id does not exist");
        }
    }

    public List<Purchase> addNewPurchases(Iterable<Purchase> purchases) {
        return purchaseRepository.saveAll(purchases);
    }
}
