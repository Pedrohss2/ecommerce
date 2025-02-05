package com.ph.ecommerce.services;

import com.ph.ecommerce.dto.ProductDTO;
import com.ph.ecommerce.entities.Product;
import com.ph.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

        return new ProductDTO(product);
    }
}
