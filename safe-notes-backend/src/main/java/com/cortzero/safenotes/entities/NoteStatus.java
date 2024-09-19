package com.cortzero.safenotes.entities;

import com.cortzero.safenotes.exceptions.StatusNotFoundException;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum NoteStatus {

    ACTIVE("Active"), ARCHIVED("Archived");

    private final String name;

    NoteStatus(String name) {
        this.name = name;
    }

    public static NoteStatus of(String statusName) {
        return Stream.of(values())
                .filter(status -> status.name.equalsIgnoreCase(statusName))
                .findFirst()
                .orElseThrow(() -> new StatusNotFoundException(statusName));
    }

    @Override
    public String toString() {
        return name;
    }
}
