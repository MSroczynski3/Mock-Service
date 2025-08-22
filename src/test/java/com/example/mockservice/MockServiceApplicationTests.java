package com.example.mockservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@Disabled("Disabled for Phase 1 to avoid Docker/Testcontainers requirement")
class MockServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
