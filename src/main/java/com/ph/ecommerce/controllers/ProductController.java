package com.ph.ecommerce.controllers;

import com.ph.ecommerce.dto.ProductDTO;
import com.ph.ecommerce.dto.error.CustomError;
import com.ph.ecommerce.services.ProductService;
import com.ph.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;


@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> dto = service.findAll(pageable);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @PathVariable Long id, @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return  ResponseEntity.noContent().build();
    }

}
