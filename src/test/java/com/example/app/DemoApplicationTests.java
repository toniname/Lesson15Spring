package com.example.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;


@WebMvcTest(NoteController.class)
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @BeforeEach
    public void setup() {
        // Налаштування поведінки мок-об'єкта
        when(noteService.listAll()).thenReturn(Collections.emptyList());
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
        note.setId(1L); // Встановлюємо id

        when(noteService.add(any(Note.class))).thenAnswer(invocation -> {
            Note n = invocation.getArgument(0);
            n.setId(1L); // Моковий сервіс встановлює id
            return n;
        });
        when(noteService.getById(1L)).thenReturn(note);

        Note addedNote = noteService.add(note);

        assertEquals("Test Note", addedNote.getTitle());
        assertEquals("This is a test note", addedNote.getContent());

        Note retrievedNote = noteService.getById(1L);
        assertEquals(addedNote, retrievedNote);
    }

    @Test
    void testDeleteById() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("This is a test note");
        note.setId(1L); // Встановлюємо id

        when(noteService.add(any(Note.class))).thenAnswer(invocation -> {
            Note n = invocation.getArgument(0);
            n.setId(1L); // Моковий сервіс встановлює id
            return n;
        });

        noteService.add(note);
        noteService.deleteById(note.getId());
        when(noteService.listAll()).thenReturn(Collections.emptyList());

        assertTrue(noteService.listAll().isEmpty());
    }

    @Test
    void testUpdate() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("This is a test note");
        note.setId(1L); // Встановлюємо id

        when(noteService.add(any(Note.class))).thenAnswer(invocation -> {
            Note n = invocation.getArgument(0);
            n.setId(1L); // Моковий сервіс встановлює id
            return n;
        });

        Note addedNote = noteService.add(note);

        addedNote.setTitle("Updated Test Note");
        addedNote.setContent("This is an updated test note");

        noteService.edit(addedNote);
        when(noteService.getById(1L)).thenReturn(addedNote);

        Note updatedNote = noteService.getById(1L);
        assertEquals("Updated Test Note", updatedNote.getTitle());
        assertEquals("This is an updated test note", updatedNote.getContent());
    }
}