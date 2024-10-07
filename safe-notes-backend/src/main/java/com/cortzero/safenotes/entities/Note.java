package com.cortzero.safenotes.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_generator")
    @SequenceGenerator(name = "note_generator", sequenceName = "note_seq", allocationSize = 1)
    @Column(name = "note_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoteStatus status;

    @Column(name = "note_date", nullable = false)
    private LocalDate date;

    @Column(length = 500)
    private String text;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "note_categories",
        joinColumns = @JoinColumn(name = "note_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    public Note(Long id, String title, NoteStatus status, LocalDate date, String text, Set<Category> categories) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.date = date;
        this.text = text;
        this.categories = categories;
    }

    public Note(Long id, String title, NoteStatus status, LocalDate date, String text) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.date = date;
        this.text = text;
        this.categories = new HashSet<>();
    }

    public void addCategory(Category newCategory) {
        categories.add(newCategory);
    }

    public void removeCategory(String categoryName) {
        categories.removeIf(category -> category.getName().equals(categoryName));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
