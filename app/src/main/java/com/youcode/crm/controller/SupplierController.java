package com.youcode.crm.controller;

import com.youcode.crm.entity.Supplier;
import com.youcode.crm.entity.dto.SupplierDTO;
import com.youcode.crm.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youcode.crm.controller.ApiMapping.SUPPLIERS_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(SUPPLIERS_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers(Pageable pageable){
        return status(HttpStatus.OK).body(supplierService.getAllSuppliers(pageable));
    }

    @GetMapping(path = "/{id}", produces= APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable final Long id){
        return status(HttpStatus.OK).body(supplierService.getSupplierById(id));
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces= APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<SupplierDTO> editSupplierContent(@RequestBody final SupplierDTO supplier){
        return status(HttpStatus.OK).body(supplierService.editSupplier(supplier));
    }

    @PostMapping(consumes=APPLICATION_JSON_VALUE, produces=APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SupplierDTO> postNewSuppiler(@RequestBody final SupplierDTO supplier){
        return status(HttpStatus.OK).body(supplierService.addNewSuppiler(supplier));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteSupplierById(@PathVariable final Long id){
        supplierService.deleteSupplierById(id);
    }

}
