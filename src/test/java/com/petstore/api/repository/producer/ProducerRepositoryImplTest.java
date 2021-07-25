package com.petstore.api.repository.producer;

import com.petstore.api.domain.producer.Producer;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProducerRepositoryImplTest {
    @InjectMocks
    private ProducerRepositoryImpl producerRepository;

    @Mock
    private ProducerMapper producerMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetAll_Should_ReturnProducerList_When_ProducerListIsNotEmpty() {
        final List<Producer> expectedResult = new ArrayList<>();
        expectedResult.add(new Producer(1, "thuc an meo"));
        expectedResult.add(new Producer(2, "thuc an cho"));

        when(producerMapper.getAll()).thenReturn(expectedResult);
        final List<Producer> actualResult = producerRepository.getAll();
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void test_GetAll_Should_ReturnEmptyList_When_ProducerListIsEmpty() {
        when(producerMapper.getAll()).thenReturn(Collections.emptyList());
        final List<Producer> actualResult = producerRepository.getAll();
        assertTrue(CollectionUtils.isEmpty(actualResult));
    }

    @Test
    void test_Add_Should_Success_When_Call() {
        doNothing().when(producerMapper).add(anyString());
        producerRepository.add(anyString());
        verify(producerMapper, times(1)).add(anyString());
    }
}