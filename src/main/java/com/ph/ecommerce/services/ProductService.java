package com.ph.ecommerce.services;

import com.ph.ecommerce.dto.ProductDTO;
import com.ph.ecommerce.entities.Product;
import com.ph.ecommerce.repositories.ProductRepository;
import com.ph.ecommerce.services.exceptions.DataBaseException;
import com.ph.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id not found"));

        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> product = repository.findAll(pageable);

       return product.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {

        Product product = new Product();

        copyToEntity(product, dto);

        product = repository.save(product);

        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {

        try {
            Product product = repository.getReferenceById(id);

            copyToEntity(product, dto);

            product = repository.save(product);

            return new ProductDTO(product);
        }
        catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Resource not found");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!repository.existsById(id)) {
           throw  new ResourceNotFoundException("Resource not found");
        }

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException error) {
            throw new DataBaseException("Integrity violation");
        }

    }

    public void copyToEntity(Product product, ProductDTO dto) {

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImgUrl(dto.getImgUrl());
        product.setPrice(dto.getPrice());

    }
}
