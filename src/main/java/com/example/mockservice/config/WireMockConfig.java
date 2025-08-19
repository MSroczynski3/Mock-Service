package com.example.mockservice.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
}
