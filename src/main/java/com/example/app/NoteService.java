package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public Note add(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    public Note edit(Note note) {
        try {
            return noteRepository.save(note);
        } catch (DuplicateKeyException e) {
            throw new NoteAlreadyExistsException("Note with the same title already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Error accessing the database", e);
        }
    }

    public Note getById(long id) {
        return noteRepository.findById(id).orElseThrow(() -> new DatabaseOperationException("Note not found with id " + id ));
    }
}

