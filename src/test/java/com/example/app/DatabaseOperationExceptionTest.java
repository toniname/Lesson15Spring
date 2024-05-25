package com.example.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DatabaseOperationExceptionTest {
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