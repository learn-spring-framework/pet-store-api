package com.petstore.api.controller.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductGetByCategoryResponse {
    private int categoryId;
    private String categoryName;
    private List<ProductRecord> products;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ProductRecord {
        private int id;
        private String code;
        private String name;
        private String producerName;
        private float price;
        private int quantityStock;
        private boolean status;

        public boolean getStatus() {
            return status;
        }
    }
}
