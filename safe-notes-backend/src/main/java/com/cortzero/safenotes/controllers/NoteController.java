package com.cortzero.safenotes.controllers;

import com.cortzero.safenotes.dtos.NoteDTO;
import com.cortzero.safenotes.services.INoteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"${cross.origin.client-url}"})
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final INoteService noteService;

    public NoteController(INoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @PostMapping
    public ResponseEntity<String> createNote(@RequestBody NoteDTO noteDTO, UriComponentsBuilder uriComponentsBuilder) {
        Long savedNoteId = noteService.createNote(noteDTO);
        URI newNoteUri = uriComponentsBuilder
                .path("/api/notes/{id}")
                .buildAndExpand(savedNoteId)
                .toUri();
        return ResponseEntity.created(newNoteUri).body("Note created successfully.");
    }

}
