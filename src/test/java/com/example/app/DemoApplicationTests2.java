package com.example.app;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@WebMvcTest(NoteController.class)
class DemoApplicationTests2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @BeforeEach
    public void setup() {
        Note note1 = new Note();
        note1.setId(1L);
        note1.setTitle("Test Note 1");
        note1.setContent("This is a test note 1.");

        Note note2 = new Note();
        note2.setId(2L);
        note2.setTitle("Test Note 2");
        note2.setContent("This is a test note 2.");

        when(noteService.listAll()).thenReturn(Arrays.asList(note1, note2));
        when(noteService.getById(1L)).thenReturn(note1);
        when(noteService.getById(2L)).thenReturn(note2);
    }

    @Test
    public void testListNotes() throws Exception {
        mockMvc.perform(get("/note/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("note_list"))
                .andExpect(model().attributeExists("notes"));
    }

    @Test
    public void testDeleteNote() throws Exception {
        mockMvc.perform(post("/note/delete")
                        .param("id", "1")
                        .with(csrf())) // Додаємо CSRF-токен до запиту
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list"));

        verify(noteService, times(1)).deleteById(1L);
    }

    @Test
    public void testEditNoteForm() throws Exception {
        mockMvc.perform(get("/note/edit")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("note_edit"))
                .andExpect(model().attributeExists("note"));
    }

    @Test
    public void testEditNote() throws Exception {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Updated Note");
        note.setContent("This is an updated note.");

        mockMvc.perform(post("/note/edit")
                        .flashAttr("note", note)
                        .with(csrf())) // Додаємо CSRF-токен до запиту
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list"));

        verify(noteService, times(1)).edit(any(Note.class));
    }
}
