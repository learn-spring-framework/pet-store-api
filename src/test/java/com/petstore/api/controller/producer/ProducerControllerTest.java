package com.petstore.api.controller.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.api.config.ApiPathConfig;
import com.petstore.api.controller.Error;
import com.petstore.api.controller.ErrorExceptionHandler;
import com.petstore.api.controller.Success;
import com.petstore.api.domain.producer.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProducerController.class)
class ProducerControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ProducerRepository producerRepository;

    @Mock
    private BindingResult bindingResult;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProducerController(producerRepository))
                .setControllerAdvice(ErrorExceptionHandler.class)
                .build();
    }

    @Test
    public void test_GetAll_Should_ReturnStatusCode404_When_WrongUrl() throws Exception {
        final String url = "/v2/producer";
        mockMvc.perform(get(url))
               .andExpect(status().isNotFound())
               .andReturn();
    }

    @Test
    public void test_GetAll_Should_ReturnStatusCode404_When_WrongMethod() throws Exception {
        final Error expectedError = Error.builder()
                                         .code(HttpStatus.NOT_FOUND.value())
                                         .message("End Point Not Found")
                                         .build();
        final String expectedErrorStr = mapper.writeValueAsString(expectedError);

        mockMvc.perform(post(ApiPathConfig.GET_ALL_PRODUCER_URL))
               .andExpect(status().isMethodNotAllowed())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(content().json(expectedErrorStr))
               .andReturn();
    }

    @Test
    public void test_GetAll_Should_ReturnStatusCode500_When_BbConnectError() throws Exception {
        final Error expectedError = Error.builder()
                                         .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                         .message("Connection Refused: Connect")
                                         .build();
        final String expectedErrorStr = mapper.writeValueAsString(expectedError);

        doThrow(new DataAccessException("Connection Refused: Connect") {
        }).when(producerRepository).getAll();

        mockMvc.perform(get(ApiPathConfig.GET_ALL_PRODUCER_URL))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedErrorStr))
                .andReturn();
    }

    @Test
    public void test_Add_Should_ReturnStatusCode404_When_WrongUrl() throws Exception {
        final String url = "/v2/producer";
        mockMvc.perform(post(url))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void test_Add_Should_ReturnStatusCode404_When_WrongMethod() throws Exception {
        final Error expectedError = Error.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("End Point Not Found")
                .build();
        final String expectedErrorStr = mapper.writeValueAsString(expectedError);

        mockMvc.perform(get(ApiPathConfig.ADD_PRODUCER_URL))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedErrorStr))
                .andReturn();
    }

    @Test
    public void test_Add_Should_ReturnStatusCode400_When_NameIsEmpty() throws Exception {
        final String name = "";
        final Error expectedError = Error.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Producer name is a required field")
                .build();
        final String expectedErrorStr = mapper.writeValueAsString(expectedError);

        final ProducerAddRequestBody producerAddRequestBody = new ProducerAddRequestBody(name);
        final String producerAddRequestBodyStr = mapper.writeValueAsString(producerAddRequestBody);

        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(producerRepository).add(anyString());

        mockMvc.perform(post(ApiPathConfig.ADD_PRODUCER_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(producerAddRequestBodyStr))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedErrorStr))
                .andReturn();
        verify(producerRepository, times(0)).add(anyString());
    }

    @Test
    public void test_Add_Should_ReturnStatusCode400_When_NameLengthGreaterThanMax() throws Exception {
        final int nameLength = 20;
        final String name = IntStream.range(1, nameLength + 1)
                .mapToObj(i -> Character.toString((char)i)).collect(Collectors.joining());

        final Error expectedError = Error.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Producer name is a required field")
                .build();
        final String expectedErrorStr = mapper.writeValueAsString(expectedError);

        final ProducerAddRequestBody producerAddRequestBody = new ProducerAddRequestBody(name);
        final String producerAddRequestBodyStr = mapper.writeValueAsString(producerAddRequestBody);

        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(producerRepository).add(anyString());

        mockMvc.perform(post(ApiPathConfig.ADD_PRODUCER_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(producerAddRequestBodyStr))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedErrorStr))
                .andReturn();
        verify(producerRepository, times(0)).add(anyString());
    }

    @Test
    public void test_Add_Should_ReturnStatusCode500_When_BbConnectError() throws Exception {

        final Error expectedError = Error.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Connection Refused: Connect")
                .build();
        final String expectedErrorStr = mapper.writeValueAsString(expectedError);

        final ProducerAddRequestBody producerAddRequestBody = new ProducerAddRequestBody("Name test");
        final String producerAddRequestBodyStr = mapper.writeValueAsString(producerAddRequestBody);

        when(bindingResult.hasErrors()).thenReturn(false);
        doThrow(new DataAccessException("Connection Refused: Connect") {
        }).when(producerRepository).add(anyString());

        mockMvc.perform(post(ApiPathConfig.ADD_PRODUCER_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(producerAddRequestBodyStr))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedErrorStr))
                .andReturn();
        verify(producerRepository, times(1)).add(anyString());
    }

    @Test
    public void test_Add_Should_ReturnStatusCode500_When_RuntimeError() throws Exception {
        final Error expectedError = Error.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Internal Server Error")
                .build();
        final String expectedErrorStr = mapper.writeValueAsString(expectedError);

        final ProducerAddRequestBody producerAddRequestBody = new ProducerAddRequestBody("Name test");
        final String producerAddRequestBodyStr = mapper.writeValueAsString(producerAddRequestBody);

        when(bindingResult.hasErrors()).thenReturn(false);
        doThrow(new RuntimeException("Internal Server Error")).when(producerRepository).add(anyString());

        mockMvc.perform(post(ApiPathConfig.ADD_PRODUCER_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(producerAddRequestBodyStr))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedErrorStr))
                .andReturn();
        verify(producerRepository, times(1)).add(anyString());
    }

    @Test
    public void test_Add_Should_ReturnStatusCode200_When_AddSuccess() throws Exception {
        final Success success = new Success(HttpStatus.OK.value(), "Add producer successfully");
        final String expectedSuccessStr = mapper.writeValueAsString(success);

        final ProducerAddRequestBody producerAddRequestBody = new ProducerAddRequestBody("Name test");
        final String producerAddRequestBodyStr = mapper.writeValueAsString(producerAddRequestBody);

        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(producerRepository).add(anyString());

        mockMvc.perform(post(ApiPathConfig.ADD_PRODUCER_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(producerAddRequestBodyStr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedSuccessStr))
                .andReturn();
        verify(producerRepository, times(1)).add(anyString());
    }
}