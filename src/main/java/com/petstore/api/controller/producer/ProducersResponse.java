package com.petstore.api.controller.producer;

import com.petstore.api.domain.producer.Producer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProducersResponse {
    private List<Producer> results;
}
