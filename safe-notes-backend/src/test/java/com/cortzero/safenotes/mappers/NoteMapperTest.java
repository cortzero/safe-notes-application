package com.cortzero.safenotes.mappers;

import com.cortzero.safenotes.dtos.NoteDTO;
import com.cortzero.safenotes.entities.Category;
import com.cortzero.safenotes.entities.Note;
import com.cortzero.safenotes.entities.NoteStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class NoteMapperTest {

    private NoteMapper noteMapper;

    @BeforeEach
    public void setUp() {
        noteMapper = new NoteMapper();
    }

    @Test
    public void NoteMapper_GetEntity_ReturnsNoteEntity() {
        // Given
        NoteDTO noteDTO = NoteDTO.builder()
                .title("Do the homework!")
                .status(NoteStatus.ACTIVE.getName())
                .date("2008-04-12")
                .text("Remember to do the math homework before April 16th")
                .build();

        // When
        Note note = noteMapper.getEntity(noteDTO);

        // Then
        assertThat(note).isNotNull();
        assertThat(note.getTitle()).isEqualTo(noteDTO.getTitle());
        assertThat(note.getStatus().getName()).isEqualTo(noteDTO.getStatus());
        assertThat(note.getDate()).isEqualTo(noteDTO.getDate());
        assertThat(note.getText()).isEqualTo(noteDTO.getText());
    }

    @Test
    public void NoteMapper_GetEntity_ThrowsIllegalArgumentException_WhenDtoIsNull() {
        assertThatIllegalArgumentException().isThrownBy(() -> noteMapper.getEntity(null))
                .withMessage("The dto must not be null");
    }

    @Test
    public void NoteMapper_GetDto_ReturnsNoteDtoFromNote() {
        // Given
        final Category category1 = Category.builder().name("school").build();
        Note note = Note.builder()
                .title("Do the homework!")
                .status(NoteStatus.ACTIVE)
                .date(LocalDate.parse("2008-04-12", DateTimeFormatter.ISO_LOCAL_DATE))
                .text("Remember to do the math homework before April 16th")
                .categories(Set.of(category1))
                .build();

        // When
        NoteDTO noteDTO = noteMapper.getDTO(note);

        // Then
        assertThat(noteDTO).isNotNull();
        assertThat(noteDTO.getTitle()).isEqualTo(note.getTitle());
        assertThat(noteDTO.getStatus()).isEqualTo(note.getStatus().toString());
        assertThat(noteDTO.getDate()).isEqualTo(note.getDate().toString());
        assertThat(noteDTO.getText()).isEqualTo(note.getText());
        assertThat(noteDTO.getCategories()).contains(category1.getName());
    }

    @Test
    public void NoteMapper_GetDto_ThrowsIllegalArgumentException_WhenNoteIsNull() {
        assertThatIllegalArgumentException().isThrownBy(() -> noteMapper.getDTO(null))
                .withMessage("The Note should not be null");
    }

}