package com.example.mockservice.controller;

import com.example.mockservice.dto.MockSummary;
import com.example.mockservice.service.MockService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MockController.class)
class MockControllerListWebMvcTest {
    private static final String ENDPOINT = "/api/mocks";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MockService mockService;

    @Test
    @DisplayName("GET /api/mocks returns empty list")
    void listMocksEmpty() throws Exception {
        when(mockService.listMocks()).thenReturn(List.of());

        mockMvc.perform(get(ENDPOINT).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /api/mocks returns summaries")
    void listMocksWithData() throws Exception {
        when(mockService.listMocks()).thenReturn(List.of(
                new MockSummary("id-1", "GET", "/foo", 200),
                new MockSummary("id-2", "POST", "/bar", 201)
        ));

        mockMvc.perform(get(ENDPOINT).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("id-1"))
                .andExpect(jsonPath("$[0].method").value("GET"))
                .andExpect(jsonPath("$[0].urlPattern").value("/foo"))
                .andExpect(jsonPath("$[0].responseStatus").value(200))
                .andExpect(jsonPath("$[1].id").value("id-2"))
                .andExpect(jsonPath("$[1].method").value("POST"))
                .andExpect(jsonPath("$[1].urlPattern").value("/bar"))
                .andExpect(jsonPath("$[1].responseStatus").value(201));

        verify(mockService).listMocks();
    }
}


