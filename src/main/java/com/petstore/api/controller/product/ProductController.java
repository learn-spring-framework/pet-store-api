package com.petstore.api.controller.product;

import com.petstore.api.config.ApiPathConfig;
import com.petstore.api.controller.Success;
import com.petstore.api.domain.product.Product;
import com.petstore.api.domain.product.ProductRepository;
import com.petstore.api.domain.product.ProductSearchRequest;
import com.petstore.api.exception.InvalidParameterException;
import com.petstore.api.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private static final String DELIMITER_CHARACTER = ", ";
    private static final String ADD_SUCCESS_MESSAGE = "Add product successfully";

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductGetByCategoryResponseFactory productGetByCategoryResponseFactory;

    @Autowired
    public ProductController(final ProductRepository productRepository,
                             final ProductService productService,
                             final ProductGetByCategoryResponseFactory productGetByCategoryResponseFactory) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.productGetByCategoryResponseFactory = productGetByCategoryResponseFactory;
    }

    @GetMapping(ApiPathConfig.GET_ALL_PRODUCT_URL)
    public ProductsSearchResponse getAll() {
        return new ProductsSearchResponse(productRepository.getAll());
    }

    @GetMapping(ApiPathConfig.SEARCH_PRODUCT_URL)
    public ProductsSearchResponse search(@Valid final ProductSearchRequest productSearchRequest,
                                         final BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResult));
        }

        if (productSearchRequest.isListAll()) {
            return new ProductsSearchResponse(productRepository.getAll());
        }

        final List<Product> products = productRepository.search(productSearchRequest);

        return new ProductsSearchResponse(products);
    }

    @GetMapping(ApiPathConfig.GET_PRODUCT_BY_CATEGORY_URL)
    public ProductGetByCategoryResponse getByCategoryId(@Valid final ProductRequest productRequest,
                                                        final BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResult));
        }

        final List<Product> products = productService.getByCategoryId(Integer.parseInt(productRequest.getCategoryId()));

        return productGetByCategoryResponseFactory.toProductGetByCategoryResponse(products);
    }

    @PostMapping(ApiPathConfig.ADD_PRODUCT_URL)
    public Success add(@Valid @RequestBody final ProductAddRequestBody productAddRequestBody,
                       final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResult));
        }
        final Product product = Product.builder()
                                       .code(productAddRequestBody.getCode())
                                       .name(productAddRequestBody.getName())
                                       .price(Float.parseFloat(productAddRequestBody.getPrice()))
                                       .quantityStock(Integer.parseInt(productAddRequestBody.getQuantityStock()))
                                       .build();
        productService.add(Integer.parseInt(productAddRequestBody.getCategoryId()),
                           Integer.parseInt(productAddRequestBody.getProducerId()), product);
        return new Success(HttpStatus.OK.value(), ADD_SUCCESS_MESSAGE);
    }

    private String getErrorMessage(final BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(DELIMITER_CHARACTER));
    }
}
