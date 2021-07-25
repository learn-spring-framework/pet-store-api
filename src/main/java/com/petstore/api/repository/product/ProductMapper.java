package com.petstore.api.repository.product;

import com.petstore.api.domain.product.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductMapper {
    @Select("SELECT `id`, `code`, `name`, `price`, `quantity_stock` as `quantityStock`, `status` " +
            "FROM `products` " +
            "WHERE `is_deleted` = 0")
    List<Product> getAll();

    @Select({"<script>",
            "SELECT `products`.`id`, `products`.`code`, `products`.`name`, `products`.`price`,",
            "`products`.`quantity_stock` as `quantityStock`, `products`.`status`",
            "FROM `products` JOIN `categories` ON  `products`.`category_id` = `categories`.`id`",
            "AND `categories`.`is_deleted` = 0",
            "JOIN `producers` ON `products`.`producer_id` = `producers`.`id`",
            "AND `producers`.`is_deleted` = 0",
            "WHERE `products`.`is_deleted` = 0",
            "<if test='categoryId != null and categoryId != \"\"'>AND `categories`.`id`=#{categoryId}</if>",
            "<if test='producerId != null and producerId != \"\"'>AND `producers`.`id`=#{producerId}</if>",
            "<if test='code != null and code != \"\"'>AND `products`.`code` LIKE CONCAT('%', #{code}, '%')</if>",
            "<if test='name != null and name != \"\"'>AND `products`.`name` LIKE CONCAT('%', #{name}, '%')</if>",
            "</script>"})
    List<Product> search(@Param("categoryId") final String categoryId,
                         @Param("producerId") final String producerId,
                         @Param("code") final String code,
                         @Param("name") final String name);

    @Insert("INSERT INTO products(code, category_id, producer_id, name, price, quantity_stock) " +
            "VALUES(#{product.code}, #{categoryId}, #{producerId}, #{product.name}, " +
            "#{product.price}, #{product.quantityStock})")
    void add(@Param("categoryId") final int categoryId,
            @Param("producerId") final int producerId,
            @Param("product") final Product product);
}
