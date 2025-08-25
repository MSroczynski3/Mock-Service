package com.example.mockservice.controller;

import com.example.mockservice.service.MockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MockController.class)
class MockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MockService mockService;

    @Test
    void listMocks_returnsEmptyArray_whenServiceReturnsNull() throws Exception {
        when(mockService.listMocks()).thenReturn(null);

        mockMvc.perform(get("/api/mocks").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}


