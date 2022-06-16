package com.youcode.crm.controller;

import com.youcode.crm.entity.Absenteeism;
import com.youcode.crm.entity.SellingInvoice;
import com.youcode.crm.entity.dto.AbsenteeismDTO;
import com.youcode.crm.entity.dto.SellingInvoiceDTO;
import com.youcode.crm.service.AbsenteeismService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(produces=APPLICATION_JSON_VALUE, path = ApiMapping.ABSENTEEISMS_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class AbsenteeismController {
    private final AbsenteeismService absenteeismService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<AbsenteeismDTO>> getAllAbsenteeisms(Pageable pageable){
        return status(HttpStatus.OK).body(absenteeismService.getAllAbsenteeisms(pageable));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<AbsenteeismDTO> getAbsenteeismById(@PathVariable final Long id){
        return status(HttpStatus.OK).body(absenteeismService.getAbsenteeismById(id));
    }

    @PostMapping(produces=APPLICATION_JSON_VALUE, consumes=APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<AbsenteeismDTO> postNewAbsenteeism(@RequestBody final AbsenteeismDTO absenteeism) {
        return status(HttpStatus.OK).body(absenteeismService.addNewAbsenteeism(absenteeism));
    }

    @PutMapping(produces=APPLICATION_JSON_VALUE, consumes=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<AbsenteeismDTO> editAbsenteeism(@RequestBody final AbsenteeismDTO absenteeism){
        return status(HttpStatus.OK).body(absenteeismService.editAbsenteeism(absenteeism));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAbsenteeismById(@PathVariable final Long id) {
        absenteeismService.deleteAbsenteeismById(id);
    }

}
