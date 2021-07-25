package com.petstore.api.repository.category;

import com.petstore.api.domain.category.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CategoryMapper {
    @Select("SELECT `id`, `name` FROM `categories` WHERE `is_deleted` = 0")
    List<Category> getAll();

    @Select("SELECT `id`, `name` FROM `categories` WHERE `is_deleted` = 0 AND id=#{id}")
    Category getById(@Param("id") final int id);

    @Insert("INSERT INTO categories(name) VALUES('#{name}')")
    void add(@Param("name") final String name);
}
