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
        MockResponse response = mockService.createMock(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Mock service is running!");
    }
}
