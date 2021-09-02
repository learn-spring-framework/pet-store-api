package com.petstore.api.repository.product;

import com.petstore.api.domain.product.ProductSearchRequest;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class ProductProvider {
    public String search(final ProductSearchRequest param) {
        return new SQL() {{
            SELECT("`products`.`id`, `products`.`code`, `products`.`name`, `products`.`price`, " +
                    "`products`.`quantity_stock` as `quantityStock`, `products`.`status`");
            FROM("`products`");
            JOIN("`categories` ON  `products`.`category_id` = `categories`.`id` AND `categories`.`is_deleted` = 0");
            JOIN("`producers` ON `products`.`producer_id` = `producers`.`id` AND `producers`.`is_deleted` = 0");
            WHERE("products.is_deleted = 0");

            if (StringUtils.hasText(param.getCategoryId())) {
                WHERE("`categories`.`id` = " + param.getCategoryId());
            }
            if (StringUtils.hasText(param.getProducerId())) {
                WHERE("`producers`.`id` = " + param.getProducerId());
            }
            if (StringUtils.hasText(param.getCode())) {
                WHERE("`products`.`code` LIKE '%" + param.getCode() + "%'");
            }
            if (StringUtils.hasText(param.getName())) {
                WHERE("`products`.`name` LIKE '%" + param.getName() + "%'");
            }
        }}.toString();
    }
}
