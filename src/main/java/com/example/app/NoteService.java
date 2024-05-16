package com.example.app;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {
    private final Map<Long, Note> notes = new HashMap<>();
    private long nextId = 1;
    private static final String MY_CONSTANT1 = "Note with id ";
    private static final String MY_CONSTANT2 = " not found";

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Note add(Note note) {
        note.setId(nextId++);
        notes.put(note.getId(), note);
        return note;
    }

    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new IllegalArgumentException(MY_CONSTANT1 + id + MY_CONSTANT2);
        }
        notes.remove(id);
    }

    public void update(Note note) {
        if (!notes.containsKey(note.getId())) {
            throw new IllegalArgumentException(MY_CONSTANT1 + note.getId() + MY_CONSTANT2);
        }
        notes.put(note.getId(), note);
    }

    public Note getById(long id) {
        Note note = notes.get(id);
        if (note == null) {
            throw new IllegalArgumentException(MY_CONSTANT1 + id + MY_CONSTANT2);
        }
        return note;
    }
}

