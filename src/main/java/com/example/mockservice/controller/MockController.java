package com.example.mockservice.controller;

import com.example.mockservice.dto.CreateMockRequest;
import com.example.mockservice.dto.MockResponse;
import com.example.mockservice.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mocks")
public class MockController {
    
    @Autowired
    private MockService mockService;
    
    @PostMapping
    public ResponseEntity<MockResponse> createMock(@RequestBody CreateMockRequest request) {
        try {
            MockResponse response = mockService.createMock(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // TODO: Enhance error handling and logging
            MockResponse errorResponse = new MockResponse(
                null,
                "Failed to create mock: " + e.getMessage(),
                null
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Mock service is running!");
    }
}
