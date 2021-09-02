package com.petstore.api.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Product {
    private int id;
    private int categoryId;
    private String categoryName;
    private String producerName;
    private String code;
    private String name;
    private float price;
    private int quantityStock;
    private boolean status;

    public boolean getStatus() {
        return status;
    }
}
