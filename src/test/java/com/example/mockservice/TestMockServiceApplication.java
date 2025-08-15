package com.example.mockservice;

import org.springframework.boot.SpringApplication;

public class TestMockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(MockServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
