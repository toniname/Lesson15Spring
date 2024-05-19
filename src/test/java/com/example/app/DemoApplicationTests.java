package com.example.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoApplicationTests {

    private NoteService noteService;

    @BeforeEach
    void setUp() {
        //noteService = new NoteService();
    }

    @Test
    void testListAll() {
        assertTrue(noteService.listAll().isEmpty());
    }

    @Test
    void testAddAndGetById() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("This is a test note");
        Note addedNote = noteService.add(note);

        assertEquals("Test Note", addedNote.getTitle());
        assertEquals("This is a test note", addedNote.getContent());

        Note retrievedNote = noteService.getById(addedNote.getId());
        assertEquals(addedNote, retrievedNote);
    }

    @Test
    void testDeleteById() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("This is a test note");
        Note addedNote = noteService.add(note);

        noteService.deleteById(addedNote.getId());
        assertTrue(noteService.listAll().isEmpty());
    }

    @Test
    void testUpdate() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("This is a test note");
        Note addedNote = noteService.add(note);

        addedNote.setTitle("Updated Test Note");
        addedNote.setContent("This is an updated test note");
        noteService.edit(addedNote);

        Note updatedNote = noteService.getById(addedNote.getId());
        assertEquals("Updated Test Note", updatedNote.getTitle());
        assertEquals("This is an updated test note", updatedNote.getContent());
    }
}