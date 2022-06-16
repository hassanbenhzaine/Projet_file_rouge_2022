package com.youcode.crm.controller;

import com.youcode.crm.entity.Product;
import com.youcode.crm.entity.dto.ProductDTO;
import com.youcode.crm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youcode.crm.controller.ApiMapping.PRODUCTS_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(PRODUCTS_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @GetMapping(produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<ProductDTO>> getAllProducts(Pageable pageable){
        return status(HttpStatus.OK).body(productService.getAllProducts(pageable));
    }

    @GetMapping(path = "/name", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<ProductDTO>> findAllByName(@RequestParam String name, Pageable pageable) {
        return status(HttpStatus.OK).body(productService.findAllByName(name, pageable));
    }

    @GetMapping(path = "/{id}", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        return status(HttpStatus.OK).body(productService.getProductById(id));
    }

    @GetMapping(path = "/type", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<ProductDTO>> findAllByProductType(@RequestParam("type") String type, Pageable pageable) {
        return status(HttpStatus.OK).body(productService.findAllByProductType(type, pageable));
    }

    @PutMapping(produces=APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<ProductDTO> editProductContent(@RequestBody ProductDTO product){
        return status(HttpStatus.OK).body(productService.editProduct(product));
    }

    @PostMapping(produces=APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDTO> postNewProduct(@RequestBody ProductDTO product) {
        return status(HttpStatus.OK).body(productService.addNewProduct(product));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }

}
