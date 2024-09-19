package com.cortzero.safenotes.services;

import com.cortzero.safenotes.dtos.NoteDTO;
import com.cortzero.safenotes.mappers.NoteMapper;
import com.cortzero.safenotes.repositories.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Long createNote(NoteDTO dto) {
        return 0L;
    }

    @Override
    public Long updateNote(NoteDTO dot) {
        return 0L;
    }

    @Override
    public void deleteNote(Long id) {

    }

    @Override
    public NoteDTO getNoteById(Long id) {
        return null;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(NoteMapper::getDTO)
                .toList();
    }
}
