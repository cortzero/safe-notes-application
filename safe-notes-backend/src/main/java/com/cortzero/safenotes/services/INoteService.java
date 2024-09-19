package com.cortzero.safenotes.services;

import com.cortzero.safenotes.dtos.NoteDTO;

import java.util.List;

public interface INoteService {

    Long createNote(NoteDTO dto);
    Long updateNote(NoteDTO dot);
    void deleteNote(Long id);
    NoteDTO getNoteById(Long id);
    List<NoteDTO> getAllNotes();

}
