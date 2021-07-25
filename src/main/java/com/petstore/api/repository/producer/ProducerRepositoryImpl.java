package com.petstore.api.repository.producer;

import com.petstore.api.domain.producer.Producer;
import com.petstore.api.domain.producer.ProducerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProducerRepositoryImpl implements ProducerRepository {
    private final ProducerMapper producerMapper;

    public ProducerRepositoryImpl(final ProducerMapper producerMapper) {
        this.producerMapper = producerMapper;
    }

    @Override
    public List<Producer> getAll() {
        return producerMapper.getAll();
    }

    @Override
    public Optional<Producer> getById(int id) {
        return Optional.ofNullable(producerMapper.getById(id));
    }

    @Override
    public void add(final String name) {
        producerMapper.add(name);
    }
}
