package com.youcode.crm.controller;

import com.youcode.crm.entity.dto.PurchasePositionDTO;
import com.youcode.crm.service.PurchasePositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youcode.crm.controller.ApiMapping.PURCHASES_POSITIONS_REST_URL;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(PURCHASES_POSITIONS_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class PurchasePositionController {
    private final PurchasePositionService purchasePositionService;

    @GetMapping
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<PurchasePositionDTO>> getAllPurchasesPositions(Pageable pageable){
        return status(HttpStatus.OK).body(purchasePositionService.getAllPurchasesPositions(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<PurchasePositionDTO> getpurchasePositiontById(@PathVariable final Long id){
        return status(HttpStatus.OK).body(purchasePositionService.getpurchasePositiontById(id));
    }

}
