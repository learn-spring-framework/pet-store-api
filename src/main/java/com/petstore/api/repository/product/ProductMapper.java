package com.petstore.api.repository.product;

import com.petstore.api.domain.product.Product;
import com.petstore.api.domain.product.ProductSearchRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductMapper {
    @Select("SELECT `id`, `code`, `name`, `price`, `quantity_stock` as `quantityStock`, `status` " +
            "FROM `products` " +
            "WHERE `is_deleted` = 0")
    List<Product> getAll();

    @SelectProvider(type = ProductProvider.class, method = "search")
    List<Product> search(final ProductSearchRequest productSearchRequest);

    @Insert("INSERT INTO products(code, category_id, producer_id, name, price, quantity_stock) " +
            "VALUES(#{product.code}, #{categoryId}, #{producerId}, #{product.name}, " +
            "#{product.price}, #{product.quantityStock})")
    void add(@Param("categoryId") final int categoryId,
             @Param("producerId") final int producerId,
             @Param("product") final Product product);

    @Select("SELECT products.id, categories.id as categoryId, categories.name as categoryName, " +
            "producers.name as producerName, products.code, products.name, products.price, " +
            "products.quantity_stock as quantityStock, products.status " +
            "FROM products JOIN categories ON products.category_id = categories.id AND categories.is_deleted = 0 " +
            "JOIN producers ON products.producer_id = producers.id AND producers.is_deleted = 0 " +
            "WHERE products.is_deleted = 0 AND products.category_id = #{categoryId}")
    List<Product> getByCategoryId(@Param("categoryId") final int categoryId);
}
