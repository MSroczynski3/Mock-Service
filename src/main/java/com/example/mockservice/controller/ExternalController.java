package com.example.mockservice.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class ExternalController {

	@Autowired
	private WireMockServer wireMockServer;

	private final RestClient restClient = RestClient.create();

	@GetMapping("/external/user")
	public ResponseEntity<String> externalUser() {
		String url = wireMockServer.baseUrl() + "/mocked/user";
		String body = restClient.get().uri(url).retrieve().body(String.class);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(body);
	}
}


