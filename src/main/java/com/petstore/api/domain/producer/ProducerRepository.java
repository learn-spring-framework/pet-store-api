package com.petstore.api.domain.producer;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository {
    List<Producer> getAll();
    Optional<Producer> getById(final int id);
    void add(final String name);
}
