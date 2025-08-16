package com.example.mockservice.dto;

import java.util.Map;

public record CreateMockRequest(
    String method,           // GET, POST, PUT, DELETE, etc.
    String urlPattern,       // /api/users, /api/users/*, etc.
    int responseStatus,      // 200, 404, 500, etc.
    String responseBody,     // JSON response body
    Map<String, String> responseHeaders  // Custom headers
) {
    // Java records automatically generate constructor, getters, equals, hashCode, toString
}
