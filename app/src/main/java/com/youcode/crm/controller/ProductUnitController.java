package com.youcode.crm.controller;

import com.youcode.crm.entity.dto.ProductUnitDTO;
import com.youcode.crm.service.ProductUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.youcode.crm.controller.ApiMapping.PRODUCT_UNITS_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(PRODUCT_UNITS_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductUnitController {
    private final ProductUnitService productUnitService;

    @GetMapping(produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<ProductUnitDTO>> getAllProductUnits(Pageable pageable){
        return status(HttpStatus.OK).body(productUnitService.getAllProductUnits(pageable));
    }
}
