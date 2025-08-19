package com.example.mockservice.controller;

import com.example.mockservice.dto.CreateMockRequest;
import com.example.mockservice.exception.GlobalExceptionHandler;
import com.example.mockservice.service.MockService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MockController.class)
@Import(GlobalExceptionHandler.class)
class GlobalExceptionHandlerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MockService mockService;

    private String validRequestJson() {
        return "{" +
                "\"method\":\"GET\"," +
                "\"urlPattern\":\"/foo\"," +
                "\"responseStatus\":200," +
                "\"responseBody\":\"{}\"," +
                "\"responseHeaders\":{}" +
                "}";
    }

    @Test
    @DisplayName("IllegalArgumentException -> 400 with generic message")
    void illegalArgumentExceptionResultsInBadRequest() throws Exception {
        when(mockService.createMock(any(CreateMockRequest.class)))
                .thenThrow(new IllegalArgumentException("bad input"));

        mockMvc.perform(post("/api/mocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestJson()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid request"));
    }

    @Test
    @DisplayName("NoSuchElementException -> 404 with generic message")
    void notFoundResultsIn404() throws Exception {
        when(mockService.createMock(any(CreateMockRequest.class)))
                .thenThrow(new NoSuchElementException("missing"));

        mockMvc.perform(post("/api/mocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestJson()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Resource not found"));
    }

    @Test
    @DisplayName("DuplicateKeyException -> 409 with generic message")
    void duplicateKeyResultsInConflict() throws Exception {
        when(mockService.createMock(any(CreateMockRequest.class)))
                .thenThrow(new DuplicateKeyException("dup"));

        mockMvc.perform(post("/api/mocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestJson()))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Conflict"));
    }

    @Test
    @DisplayName("Generic Exception -> 500 with generic message")
    void genericExceptionResultsIn500() throws Exception {
        when(mockService.createMock(any(CreateMockRequest.class)))
                .thenThrow(new RuntimeException("boom"));

        mockMvc.perform(post("/api/mocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestJson()))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Internal error"));
    }
}


