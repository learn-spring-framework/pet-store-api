package com.petstore.api.repository.producer;

import com.petstore.api.domain.producer.Producer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProducerMapper {
    @Select("SELECT `id`, `name` FROM `producers` WHERE `is_deleted` = 0")
    List<Producer> getAll();

    @Select("SELECT `id`, `name` FROM `producers` WHERE `is_deleted` = 0 AND id=#{id}")
    Producer getById(final int id);

    @Insert("INSERT INTO producers(name) VALUES(#{name})")
    void add(@Param("name") final String name);
}
