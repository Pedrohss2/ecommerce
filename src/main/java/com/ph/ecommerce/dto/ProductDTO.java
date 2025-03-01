package com.ph.ecommerce.dto;

import com.ph.ecommerce.entities.Product;
import jakarta.validation.constraints.*;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "Field name cannot be blank")
    @Size(min = 3, max = 80)
    private String name;

    @Size(min = 10, max = 100)
    private String description;

    @Positive(message = "Price cannot be negative")
    private Double price;

    private String imgUrl;

    public ProductDTO() {};

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
