package com.example.mockservice.exception;

import com.example.mockservice.dto.MockResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MockResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		log.warn("Bad request: {}", ex.getMessage());
		MockResponse body = new MockResponse(null, "Invalid request", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<MockResponse> handleNotFound(RuntimeException ex) {
		log.warn("Not found: {}", ex.getMessage());
		MockResponse body = new MockResponse(null, "Resource not found", null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<MockResponse> handleDuplicateKey(DuplicateKeyException ex) {
		log.warn("Conflict: {}", ex.getMessage());
		MockResponse body = new MockResponse(null, "Conflict", null);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MockResponse> handleGeneric(Exception ex) {
		log.error("Unhandled error", ex);
		MockResponse body = new MockResponse(null, "Internal error", null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}
}


