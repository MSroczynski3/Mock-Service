package com.example.mockservice.dto;

public record MockSummary(
    String id,
    String method,
    String urlPattern,
    int responseStatus
) {}


