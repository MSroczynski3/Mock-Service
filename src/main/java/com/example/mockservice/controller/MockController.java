package com.example.mockservice.controller;

import com.example.mockservice.dto.CreateMockRequest;
import com.example.mockservice.dto.MockResponse;
import com.example.mockservice.dto.MockSummary;
import com.example.mockservice.service.MockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mocks")
@Tag(name = "Mock Management", description = "API for creating and managing HTTP mocks")
public class MockController {
    
    @Autowired
    private MockService mockService;
    
    @PostMapping
    @Operation(summary = "Create a new mock", description = "Creates a new HTTP mock with the specified method, URL pattern, response status, and body")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mock created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<MockResponse> createMock(@RequestBody CreateMockRequest request) {
        MockResponse response = mockService.createMock(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "List all mocks", description = "Returns a list of all configured mocks")
    @ApiResponse(responseCode = "200", description = "List of mocks retrieved successfully")
    public ResponseEntity<List<MockSummary>> listMocks() {
        List<MockSummary> mocks = mockService.listMocks();
        if (mocks == null) {
            mocks = java.util.Collections.emptyList();
        }
        return ResponseEntity.ok(mocks);
    }
    
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns the health status of the mock service")
    @ApiResponse(responseCode = "200", description = "Service is running")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Mock service is running!");
    }
}
