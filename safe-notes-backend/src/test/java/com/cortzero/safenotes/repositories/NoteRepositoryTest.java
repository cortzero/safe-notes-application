package com.cortzero.safenotes.repositories;

import static org.assertj.core.api.Assertions.*;
import com.cortzero.safenotes.entities.Category;
import com.cortzero.safenotes.entities.Note;
import com.cortzero.safenotes.entities.NoteStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void NoteRepository_Save_ReturnSavedNote() {
        // Given
        final String NOTE_TITLE = "Do the homework!";
        final String NOTE_CREATION_DATE = "2008-04-12";
        final Category category1 = Category.builder().name("school").build();
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .status(NoteStatus.ACTIVE)
                .date(LocalDate.parse(NOTE_CREATION_DATE, DateTimeFormatter.ISO_LOCAL_DATE))
                .text("Remember to do the math homework before April 16th")
                .categories(Set.of(category1))
                .build();

        categoryRepository.save(category1);

        // When
        Note savedNote = noteRepository.save(note);

        // Then
        assertThat(savedNote).isNotNull();
        assertThat(savedNote.getId()).isGreaterThan(0);
        assertThat(savedNote.getTitle()).isEqualTo(NOTE_TITLE);
        assertThat(savedNote.getDate().toString()).isEqualTo(NOTE_CREATION_DATE);
        assertThat(savedNote.getCategories()).contains(category1);
    }

    @Test
    public void NoteRepository_FindAll_ReturnMoreThanOneNote() {
        // Given
        final String NOTE_CREATION_DATE = "2008-04-12";
        final Category category1 = Category.builder().name("school").build();
        Note note1 = Note.builder()
                .title("Do the homework!")
                .status(NoteStatus.ACTIVE)
                .date(LocalDate.parse(NOTE_CREATION_DATE, DateTimeFormatter.ISO_LOCAL_DATE))
                .text("Remember to do the math homework before April 16th")
                .categories(Set.of(category1))
                .build();
        Note note2 = Note.builder()
                .title("Go shopping")
                .status(NoteStatus.ACTIVE)
                .date(LocalDate.parse(NOTE_CREATION_DATE, DateTimeFormatter.ISO_LOCAL_DATE))
                .text("I need to go to the store to buy some things for school.")
                .categories(Set.of(category1))
                .build();

        categoryRepository.save(category1);
        noteRepository.save(note1);
        noteRepository.save(note2);

        // When
        List<Note> noteList = noteRepository.findAll();

        // Then
        assertThat(noteList).isNotNull();
        assertThat(noteList.size()).isEqualTo(2);
        assertThat(noteList).contains(note1, note2);
    }

    @Test
    public void NoteRepository_FindById_ReturnOneNote() {
        // Given
        final String NOTE_TITLE = "Do the homework!";
        final String NOTE_CREATION_DATE = "2008-04-12";
        final Category category1 = Category.builder().name("school").build();
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .status(NoteStatus.ACTIVE)
                .date(LocalDate.parse(NOTE_CREATION_DATE, DateTimeFormatter.ISO_LOCAL_DATE))
                .text("Remember to do the math homework before April 16th")
                .categories(Set.of(category1))
                .build();

        categoryRepository.save(category1);
        noteRepository.save(note);

        // When
        Optional<Note> optionalNote = noteRepository.findById(note.getId());

        // Then
        assertThat(optionalNote).isPresent();
        assertThat(optionalNote.get().getTitle()).isEqualTo(NOTE_TITLE);
        assertThat(optionalNote.get().getDate().toString()).isEqualTo(NOTE_CREATION_DATE);
        assertThat(optionalNote.get().getCategories()).contains(category1);
    }

}
