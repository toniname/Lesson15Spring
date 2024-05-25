package com.example.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
@Slf4j
class DatabaseOperationExceptionTest {
    private static final Logger log = LoggerFactory.getLogger(DatabaseOperationExceptionTest.class);

    @Test
    void infoTest(){

        log.info("hello");
    }

    @Test
     void testConstructorWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Test cause");

        DatabaseOperationException exception = new DatabaseOperationException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
     void testConstructorWithMessageOnly() {
        String message = "Test message";

        DatabaseOperationException exception = new DatabaseOperationException(message);

        assertEquals(message, exception.getMessage());
        // Перевірка, що cause дорівнює null
        assertNull(exception.getCause());

    }
}