package com.petstore.api.config;

public class ApiPathConfig {
    public ApiPathConfig() {

    }

    public static final String GET_ALL_CATEGORY_URL = "/v1/categories";
    public static final String ADD_CATEGORY_URL = "/v1/category";
    public static final String GET_ALL_PRODUCER_URL = "/v1/producers";
    public static final String ADD_PRODUCER_URL = "/v1/producer";
    public static final String GET_ALL_PRODUCT_URL = "/v1/products";
    public static final String SEARCH_PRODUCT_URL = "/v1/product/search";
    public static final String ADD_PRODUCT_URL = "/v1/product";
    public static final String GET_PRODUCT_BY_CATEGORY_URL = "/v1/product/category/{categoryId}";
}
