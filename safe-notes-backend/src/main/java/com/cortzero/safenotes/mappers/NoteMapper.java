package com.cortzero.safenotes.mappers;

import com.cortzero.safenotes.dtos.NoteDTO;
import com.cortzero.safenotes.entities.Category;
import com.cortzero.safenotes.entities.Note;
import com.cortzero.safenotes.entities.NoteStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class NoteMapper {

    public Note getEntity(NoteDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("The dto must not be null");
        return Note.builder()
                .title(dto.getTitle())
                .status(NoteStatus.of(dto.getStatus()))
                .date(LocalDate.parse(dto.getDate(), DateTimeFormatter.ISO_LOCAL_DATE))
                .text(dto.getText())
                .build();
    }

    public NoteDTO getDTO(Note note) {
        if (note == null)
            throw new IllegalArgumentException("The Note should not be null");
        return NoteDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .status(note.getStatus().getName())
                .date(note.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .text(note.getText())
                .categories(note.getCategories().stream()
                        .map(Category::getName)
                        .collect(Collectors.toSet()))
                .build();
    }

}
