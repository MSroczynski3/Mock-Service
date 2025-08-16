package com.example.mockservice.dto;

public record MockResponse(
    String id,          // Unique identifier for the mock
    String message,     // Success/error message
    String mockUrl      // URL where the mock is available
) {
    // Java records automatically generate constructor, getters, equals, hashCode, toString
}
