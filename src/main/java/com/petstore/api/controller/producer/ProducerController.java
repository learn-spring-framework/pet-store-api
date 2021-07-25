package com.petstore.api.controller.producer;

import com.petstore.api.config.ApiPathConfig;
import com.petstore.api.controller.Success;
import com.petstore.api.domain.producer.ProducerRepository;
import com.petstore.api.exception.InvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class ProducerController {

    private static final String ADD_SUCCESS_MESSAGE = "Add producer successfully";
    private static final String DELIMITER = ", ";

    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerController(final ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @GetMapping(ApiPathConfig.GET_ALL_PRODUCER_URL)
    public ProducersResponse getAll() {
        return new ProducersResponse(producerRepository.getAll());
    }

    @PostMapping(ApiPathConfig.ADD_PRODUCER_URL)
    public Success add(@Valid @RequestBody final ProducerAddRequestBody request,
                       final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResult));
        }

        producerRepository.add(request.getName());

        return new Success(HttpStatus.OK.value(), ADD_SUCCESS_MESSAGE);
    }

    private String getErrorMessage(final BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(DELIMITER));
    }
}