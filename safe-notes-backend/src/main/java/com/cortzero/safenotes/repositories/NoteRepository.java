package com.cortzero.safenotes.repositories;

import com.cortzero.safenotes.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
