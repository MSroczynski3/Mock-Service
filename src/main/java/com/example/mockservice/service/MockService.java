package com.example.mockservice.service;

import com.example.mockservice.dto.CreateMockRequest;
import com.example.mockservice.dto.MockResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Service
public class MockService {
    
    @Autowired
    private WireMockServer wireMockServer;
    
    public MockResponse createMock(CreateMockRequest request) {
        // Generate unique ID for this mock
        String mockId = UUID.randomUUID().toString();
        
        // Create WireMock stub based on the request
        wireMockServer.stubFor(
            request(request.method(), urlPathMatching(request.urlPattern()))
                .willReturn(
                    aResponse()
                        .withStatus(request.responseStatus())
                        .withBody(request.responseBody())
                        .withHeaders(createHttpHeaders(request))
                )
        );
        
        // Build the mock URL using the WireMockServer base URL and a safe, non-regex path
        String baseUrl = wireMockServer.baseUrl();
        String safePath = deriveSafePathFromPattern(request.urlPattern());
        String mockUrl = safePath.isEmpty() ? baseUrl : baseUrl + safePath;
        
        return new MockResponse(
            mockId,
            "Mock created successfully for " + request.method() + " " + request.urlPattern(),
            mockUrl
        );
    }
    
    private String deriveSafePathFromPattern(String urlPattern) {
        if (urlPattern == null || urlPattern.isEmpty()) {
            return "";
        }
        String pattern = urlPattern.startsWith("^") ? urlPattern.substring(1) : urlPattern;
        StringBuilder safe = new StringBuilder();
        boolean escaping = false;
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (escaping) {
                safe.append(ch);
                escaping = false;
                continue;
            }
            if (ch == '\\') {
                escaping = true;
                continue;
            }
            if (".^$|?*+()[]{}".indexOf(ch) >= 0) {
                break;
            }
            safe.append(ch);
        }
        String safePath = safe.toString();
        if (!safePath.isEmpty() && safePath.charAt(0) != '/') {
            safePath = "/" + safePath;
        }
        return safePath;
    }

    private HttpHeaders createHttpHeaders(CreateMockRequest request) {
        List<HttpHeader> headerList = new ArrayList<>();
        
        // Add default Content-Type if not specified
        if (request.responseHeaders() == null || !request.responseHeaders().containsKey("Content-Type")) {
            headerList.add(new HttpHeader("Content-Type", "application/json"));
        }
        
        // Add custom headers if provided
        if (request.responseHeaders() != null) {
            request.responseHeaders().forEach((key, value) -> 
                headerList.add(new HttpHeader(key, value))
            );
        }
        
        return new HttpHeaders(headerList);
    }
}
