package com.example.mockservice.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Configuration
public class WireMockConfig {
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Primary
    public WireMockServer wireMockServer() {
        WireMockServer server = new WireMockServer(
            WireMockConfiguration.wireMockConfig()
                .port(9090)  // Different port from the main app (8080)
                .enableBrowserProxying(true)
        );
        return server;
    }

    @Bean
    public CommandLineRunner wireMockDefaultStubs(WireMockServer wireMockServer) {
        return args -> {
            wireMockServer.stubFor(
                get(urlEqualTo("/mocked/user"))
                    .willReturn(
                        aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody("{\"id\":123,\"name\":\"Mock User\"}")
                    )
            );
        };
    }
}
