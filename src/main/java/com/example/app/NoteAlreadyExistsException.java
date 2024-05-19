package com.example.app;

public class NoteAlreadyExistsException extends RuntimeException {
    public NoteAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
