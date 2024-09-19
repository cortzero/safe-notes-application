package com.cortzero.safenotes.mappers;

import com.cortzero.safenotes.dtos.NoteDTO;
import com.cortzero.safenotes.entities.Category;
import com.cortzero.safenotes.entities.Note;
import com.cortzero.safenotes.entities.NoteStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class NoteMapper {

    public static Note getEntity(NoteDTO dto) {
        return Note.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .status(NoteStatus.of(dto.getStatus()))
                .date(LocalDate.parse(dto.getDate(), DateTimeFormatter.ISO_LOCAL_DATE))
                .text(dto.getText())
                .build();
    }

    public static NoteDTO getDTO(Note note) {
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
