package com.ph.ecommerce.controllers;

import com.ph.ecommerce.dto.ProductDTO;
import com.ph.ecommerce.entities.Product;
import com.ph.ecommerce.repositories.ProductRepository;
import com.ph.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
       ProductDTO dto = service.findById(id);

        return ResponseEntity.ok().body(dto);
    }
}
