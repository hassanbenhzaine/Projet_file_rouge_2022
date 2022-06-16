package com.youcode.crm.controller;

import com.youcode.crm.entity.SellingInvoice;
import com.youcode.crm.entity.dto.SellingInvoiceDTO;
import com.youcode.crm.service.SellingInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youcode.crm.controller.ApiMapping.INVOICES_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(INVOICES_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class SellingInvoiceController {
    private final SellingInvoiceService sellingInvoiceService;

    @GetMapping(produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<SellingInvoiceDTO>> getAllSellingInvoices(Pageable pageable){
        return status(HttpStatus.OK).body(sellingInvoiceService.getAllSellingInvoices(pageable));
    }

    @GetMapping(value = "/{id}", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<SellingInvoiceDTO> getInvoiceById(@PathVariable final Long id){
        return status(HttpStatus.OK).body(sellingInvoiceService.getInvoiceById(id));
    }

    @PutMapping(produces=APPLICATION_JSON_VALUE, consumes=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SellingInvoiceDTO> editSellingInvoice(@RequestBody final SellingInvoiceDTO sellingInvoice){
        return status(HttpStatus.OK).body(sellingInvoiceService.editSellingInvoice(sellingInvoice));
    }

    @PostMapping(consumes=APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<SellingInvoiceDTO> postNewInvoice(@RequestBody final SellingInvoiceDTO sellingInvoice) {
        return status(HttpStatus.OK).body(sellingInvoiceService.addNewInvoice(sellingInvoice));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteInvoiceById(@PathVariable final Long id){
        sellingInvoiceService.deleteInvoiceById(id);
    }
}
