package com.cortzero.safenotes.controllers;

import com.cortzero.safenotes.dtos.NoteDTO;
import com.cortzero.safenotes.services.NoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.*;

@WebMvcTest(controllers = NoteController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class NoteControllerTest {

    @MockBean
    private NoteService noteService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void NoteController_GetAllNotes_Returns200HttpCode() throws Exception {
        // Given
        final NoteDTO noteDTO1 = NoteDTO.builder()
                .id(1L)
                .title("Do the homework!")
                .status("Active")
                .date("2008-04-12")
                .text("Remember to do the math homework before April 16th")
                .categories(Set.of("school"))
                .build();
        final NoteDTO noteDTO2 = NoteDTO.builder()
                .id(2L)
                .title("Go shopping")
                .status("Active")
                .date("2010-09-01")
                .text("I need to go to the store to buy some things for school.")
                .categories(Set.of("school"))
                .build();
        List<NoteDTO> noteDTOList = new ArrayList<>(List.of(noteDTO1, noteDTO2));
        given(noteService.getAllNotes()).willAnswer(invocationOnMock -> noteDTOList);

        // When
        ResultActions response = mockMvc.perform(get("/api/notes"));

        // Then
        response.andExpect(status().isOk());

        DocumentContext documentContext = JsonPath.parse(response.andReturn().getResponse().getContentAsString());
        int numberOfNotesReturned = documentContext.read("$.length()");
        assertThat(numberOfNotesReturned).isEqualTo(2);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactly(1, 2);

        JSONArray titles = documentContext.read("$..title");
        assertThat(titles).containsExactly("Do the homework!", "Go shopping");

        JSONArray status = documentContext.read("$..status");
        assertThat(status).containsExactly("Active", "Active");

        JSONArray dates = documentContext.read("$..date");
        assertThat(dates).containsExactly("2008-04-12", "2010-09-01");

        JSONArray texts = documentContext.read("$..text");
        assertThat(texts).containsExactly(
                "Remember to do the math homework before April 16th",
                "I need to go to the store to buy some things for school."
        );

        JSONArray categories = documentContext.read("$..categories");
        assertThat(categories).contains(
                List.of("school"),
                List.of("school")
        );

        verify(noteService, times(1)).getAllNotes();
    }

    @Test
    public void NoteController_CreateNote_Returns201HttpCode() throws Exception {
        // Given
        final NoteDTO noteDTO = NoteDTO.builder()
                .id(1L)
                .title("Do the homework!")
                .status("Active")
                .date("2008-04-12")
                .text("Remember to do the math homework before April 16th")
                .categories(Set.of("school"))
                .build();

        given(noteService.createNote(noteDTO)).willReturn(1L);

        // When
        ResultActions response = mockMvc.perform(post("/api/notes")
                .content(objectMapper.writeValueAsString(noteDTO))
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andExpect(status().isCreated());
        assertThat(response.andReturn().getResponse().getContentAsString())
                .isEqualTo("Note created successfully.");
    }

}