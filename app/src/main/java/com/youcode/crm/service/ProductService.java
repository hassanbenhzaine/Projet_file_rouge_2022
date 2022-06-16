package com.youcode.crm.service;

import com.youcode.crm.entity.Product;
import com.youcode.crm.entity.dto.ProductDTO;
import com.youcode.crm.mapper.ProductMapper;
import com.youcode.crm.repository.ProductRepository;
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
public class ProductService implements CurrentTimeInterface{

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * The method is to retrieve all products from the database and display them.
     *
     * After downloading all the data about the product,
     * the data is mapped to dto which will display only those needed
     * @return list of all products with specification of data in ProductDTO
     */
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts(Pageable pageable){
        return productRepository.findAll(pageable)
                .stream()
                .map(productMapper::mapProductToDto)
                .collect(Collectors.toList());
    }

    /**
     * The method is to download a specific products from the database and display it.
     * After downloading all the data about the products,
     * the data is mapped to dto which will display only those needed
     *
     * @param id id of the product to be searched for
     * @throws ResponseStatusException if the id of the product you are looking for does not exist throws 404 status
     * @return detailed data about a specific product
     */
    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product cannot be found, the specified id does not exist"));
        return productMapper.mapProductToDto(product);
    }

    @Transactional
    public ProductDTO editProduct(ProductDTO product){
        Product mappedProduct = productMapper.mapProductDtoToProduct(product);
        Product editedProduct = productRepository.findById(mappedProduct.getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product cannot be found"));
        editedProduct.setName(mappedProduct.getName());
        editedProduct.setSellingPrice(mappedProduct.getSellingPrice());
        editedProduct.setPurchasePrice(mappedProduct.getPurchasePrice());
        editedProduct.setTaxRate(mappedProduct.getTaxRate());

        return productMapper.mapProductToDto(editedProduct);
    }

    /**
     * The task of the method is to add a product to the database.
     * @param product requestbody of the customer to be saved
     * @return saving the product to the database
     */
    public ProductDTO addNewProduct(ProductDTO product) {
        Product addedProduct = productRepository.save(productMapper.mapProductDtoToProduct(product));
        return productMapper.mapProductToDto(addedProduct);
    }

    /**
     * Method deletes the selected product by id
     * @param id id of the product to be deleted
     * @throws ResponseStatusException if id of the product is incorrect throws 404 status with message
     */
    public void deleteProductById(Long id) {
        try{
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The specified id does not exist");
        }
    }



    /**
     * The method is to retrieve products whose have the name specified by the user.
     * After downloading all the data about the product,
     * the data is mapped to dto which will display only those needed
     * @param name name of the product
     * @return details of specific products
     */
    @Transactional(readOnly = true)
    public List<ProductDTO> findAllByName(String name, Pageable pageable) {
        return productRepository.findProductsByNameContaining(name, pageable)
                .stream()
                .map(productMapper::mapProductToDto)
                .collect(Collectors.toList());
    }

    /**
     * The method is to retrieve products whose have the type specified by the user.
     * After downloading all the data about the product,
     * the data is mapped to dto which will display only those needed
     * @param type type of the product
     * @return details of specific products
     */
    @Transactional(readOnly = true)
    public List<ProductDTO> findAllByProductType(String type, Pageable pageable){
        return productRepository.findProductsByTypeFullNameContaining(type, pageable)
                .stream()
                .map(productMapper::mapProductToDto)
                .collect(Collectors.toList());
    }

    public List<Product> addNewProducts(Iterable<Product> products) {
        return productRepository.saveAll(products);
    }
}
