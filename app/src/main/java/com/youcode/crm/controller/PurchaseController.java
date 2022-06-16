package com.youcode.crm.controller;

import com.youcode.crm.entity.Purchase;
import com.youcode.crm.entity.dto.PurchaseDTO;
import com.youcode.crm.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youcode.crm.controller.ApiMapping.PURCHASES_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(PURCHASES_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping(produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases(Pageable pageable){
        return status(HttpStatus.OK).body(purchaseService.getAllPurchases(pageable));
    }

    @GetMapping(path = "/{id}", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable final Long id){
        return status(HttpStatus.OK).body(purchaseService.getPurchaseById(id));
    }

    @PostMapping(consumes=APPLICATION_JSON_VALUE, produces=APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<PurchaseDTO> postNewPurchase(@RequestBody final PurchaseDTO purchase) {
        return status(HttpStatus.OK).body(purchaseService.addNewPurchase(purchase));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deletePurchaseById(@PathVariable final Long id){
       purchaseService.deletePurchaseById(id);
    }
}
