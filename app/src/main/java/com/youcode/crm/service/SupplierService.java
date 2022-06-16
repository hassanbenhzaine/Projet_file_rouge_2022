package com.youcode.crm.service;

import com.youcode.crm.entity.Supplier;
import com.youcode.crm.entity.dto.SupplierDTO;
import com.youcode.crm.mapper.SupplierMapper;
import com.youcode.crm.repository.SupplierRepository;
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
public class SupplierService implements CurrentTimeInterface{

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    /**
     * The method is to retrieve all suppliers from the database and display them.
     *
     * After downloading all the data about the supplier,
     * the data is mapped to dto which will display only those needed
     * @return list of all suppliers with specification of data in SupplierToDTO
     */
    @Transactional(readOnly = true)
    public List<SupplierDTO> getAllSuppliers(Pageable pageable){
        return supplierRepository.findAll(pageable)
                .stream()
                .map(supplierMapper::mapSupplierToDTO)
                .collect(Collectors.toList());
    }

    /**
     * The method is to download a specific supplier from the database and display it.
     * After downloading all the data about the supplier,
     * the data is mapped to dto which will display only those needed
     *
     * @param id id of the supplier to be searched for
     * @throws ResponseStatusException if the id of the supplier you are looking for does not exist throws 404 status
     * @return detailed data about a specific supplier
     */
    @Transactional(readOnly = true)
    public SupplierDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier cannot be found, the specified id does not exist"));
        return supplierMapper.mapSupplierToDTO(supplier);
    }

    /**
     * The task of the method is to add a supplier to the database.
     * @param supplier requestbody of the supplier to be saved
     * @return saving the supplier to the database
     */
    public SupplierDTO addNewSuppiler(SupplierDTO supplier){
        Supplier addedSupplier = supplierRepository.save(supplierMapper.mapSupplierDtoToSupplier(supplier));
        return supplierMapper.mapSupplierToDTO(addedSupplier);
    }

    /**
     * Method deletes the selected supplier by id
     * @param id id of the supplier to be deleted
     * @throws ResponseStatusException if id of the supplier is incorrect throws 404 status with message
     */
    public void deleteSupplierById(Long id) {
        try {
            supplierRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The specified id does not exist");
        }
    }

    /**
     * Method enabling editing name and activicity status of the selected supplier.
     * @param supplier requestbody of the supplier to be edited
     * @return edited supplier
     */
    public SupplierDTO editSupplier(SupplierDTO supplier){
        Supplier mappedSupplier = supplierMapper.mapSupplierDtoToSupplier(supplier);
        Supplier editedSupplier = supplierRepository.findById(mappedSupplier.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier does not exist"));
        editedSupplier.setName(mappedSupplier.getName());
        editedSupplier.setActivityStatus(mappedSupplier.getActivityStatus());
        return supplierMapper.mapSupplierToDTO(editedSupplier);
    }


    public List<Supplier> addNewSuppliers(Iterable<Supplier> suppliers) {
        return supplierRepository.saveAll(suppliers);
    }
}
