package com.cortzero.safenotes.services;

import com.cortzero.safenotes.dtos.CategoryDTO;
import com.cortzero.safenotes.dtos.NoteDTO;
import com.cortzero.safenotes.entities.Category;
import com.cortzero.safenotes.entities.Note;
import com.cortzero.safenotes.mappers.CategoryMapper;
import com.cortzero.safenotes.mappers.NoteMapper;
import com.cortzero.safenotes.repositories.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {

    private final NoteRepository noteRepository;
    private final CategoryService categoryService;
    private final NoteMapper noteMapper;
    private final CategoryMapper categoryMapper;

    public NoteService(NoteRepository noteRepository,
                       CategoryService categoryService,
                       NoteMapper noteMapper,
                       CategoryMapper categoryMapper) {
        this.noteRepository = noteRepository;
        this.categoryService = categoryService;
        this.noteMapper = noteMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Long createNote(NoteDTO dto) {
        Set<CategoryDTO> categoryDTOS = dto.getCategories().stream()
                .map(categoryService::getCategoryByName)
                .collect(Collectors.toSet());
        Set<Category> categoryEntities = categoryDTOS.stream()
                .map(categoryMapper::getEntity)
                .collect(Collectors.toSet());
        Note note = noteMapper.getEntity(dto);
        note.setCategories(categoryEntities);
        Note savedNote = noteRepository.save(note);
        return savedNote.getId();
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
                .map(noteMapper::getDTO)
                .toList();
    }
}
