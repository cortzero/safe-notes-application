package com.cortzero.safenotes.services;

import com.cortzero.safenotes.dtos.NoteDTO;
import com.cortzero.safenotes.entities.Category;
import com.cortzero.safenotes.entities.Note;
import com.cortzero.safenotes.entities.NoteStatus;
import com.cortzero.safenotes.mappers.NoteMapper;
import com.cortzero.safenotes.repositories.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTests {

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private NoteMapper noteMapper;

    @InjectMocks
    private NoteService noteService;

    @Test
    public void NoteService_GetAllNotes_ReturnsAllNotesDto() {
        // Given
        final Category category1 = Category.builder().name("school").build();
        final Note note1 = Note.builder()
                .id(1L)
                .title("Do the homework!")
                .status(NoteStatus.ACTIVE)
                .date(LocalDate.parse("2008-04-12", DateTimeFormatter.ISO_LOCAL_DATE))
                .text("Remember to do the math homework before April 16th")
                .categories(Set.of(category1))
                .build();
        final Note note2 = Note.builder()
                .id(2L)
                .title("Go shopping")
                .status(NoteStatus.ACTIVE)
                .date(LocalDate.parse("2010-09-01", DateTimeFormatter.ISO_LOCAL_DATE))
                .text("I need to go to the store to buy some things for school.")
                .categories(Set.of(category1))
                .build();

        final NoteDTO noteDTO1 = NoteDTO.builder()
                .id(1L)
                .title("Do the homework!")
                .status("Active")
                .date("2008-04-12")
                .text("Remember to do the math homework before April 16th")
                .categories(Set.of(category1.getName()))
                .build();
        final NoteDTO noteDTO2 = NoteDTO.builder()
                .id(2L)
                .title("Go shopping")
                .status("Active")
                .date("2010-09-01")
                .text("I need to go to the store to buy some things for school.")
                .categories(Set.of(category1.getName()))
                .build();
        List<Note> notes = new ArrayList<>(List.of(note1, note2));
        when(noteRepository.findAll()).thenReturn(notes);
        when(noteMapper.getDTO(any(Note.class))).thenReturn(noteDTO1, noteDTO2);

        // When
        List<NoteDTO> noteDTOList = noteService.getAllNotes();

        // Then
        assertThat(noteDTOList.size()).isEqualTo(2);
        assertThat(noteDTOList).containsExactly(noteDTO1, noteDTO2);

        verify(noteRepository, times(1)).findAll();
        verify(noteMapper, times(notes.size())).getDTO(any(Note.class));
    }

}
