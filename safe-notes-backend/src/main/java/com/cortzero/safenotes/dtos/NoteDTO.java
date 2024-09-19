package com.cortzero.safenotes.dtos;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class NoteDTO {

    private final Long id;
    private final String title;
    private final String status;
    private final String date;
    private final String text;
    private final Set<String> categories;

}
