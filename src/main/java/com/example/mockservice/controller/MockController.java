package com.example.mockservice.controller;

import com.example.mockservice.dto.CreateMockRequest;
import com.example.mockservice.dto.MockResponse;
import com.example.mockservice.dto.MockSummary;
import com.example.mockservice.service.MockService;
import java.util.List;
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
    
    @GetMapping
    public ResponseEntity<List<MockSummary>> listMocks() {
        List<MockSummary> mocks = mockService.listMocks();
        if (mocks == null) {
            mocks = java.util.Collections.emptyList();
        }
        return ResponseEntity.ok(mocks);
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Mock service is running!");
    }
}
