package com.example.mockservice.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@Tag(name = "External API", description = "Endpoints that proxy to mocked external services")
public class ExternalController {

	@Autowired
	private WireMockServer wireMockServer;

	private final RestClient restClient = RestClient.create();

	@GetMapping("/external/user")
	@Operation(summary = "Get mocked user data", description = "Retrieves user data from the mocked endpoint")
	@ApiResponse(responseCode = "200", description = "User data retrieved successfully")
	public ResponseEntity<String> externalUser() {
		String url = wireMockServer.baseUrl() + "/mocked/user";
		String body = restClient.get().uri(url).retrieve().body(String.class);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(body);
	}
}


