package com.aleksejkam.albums.controller;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FavoriteArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void shouldGetEmptyUserFavoriteArtists() throws Exception {
        // execute
        MvcResult result = this.mockMvc.perform(get("/users/1/favorite-artists")).andDo(print()).andExpect(status().isOk())
                .andReturn();

        // verify
        assertThat(result.getResponse().getContentAsString()).isEqualTo("[]");
    }

    @Test
    @Order(2)
    public void shouldAddUserFavoriteArtist() throws Exception {
        // execute
        this.mockMvc.perform(post("/users/1/favorite-artists/3492")).andDo(print()).andExpect(status().isOk());

        // verify
        MvcResult resultForUser1 = this.mockMvc.perform(get("/users/1/favorite-artists")).andDo(print()).andExpect(status().isOk())
                .andReturn();
        assertThat(resultForUser1.getResponse().getContentAsString()).isEqualTo("[3492]");

        MvcResult resultForUser2 = this.mockMvc.perform(get("/users/2/favorite-artists")).andDo(print()).andExpect(status().isOk())
                .andReturn();
        assertThat(resultForUser2.getResponse().getContentAsString()).isEqualTo("[]");
    }

    @Test
    @Order(3)
    public void shouldRemoveUserFavoriteArtist() throws Exception {
        // execute
        this.mockMvc.perform(delete("/users/1/favorite-artists/3492")).andDo(print()).andExpect(status().isOk());

        // verify
        MvcResult result = this.mockMvc.perform(get("/users/1/favorite-artists")).andDo(print()).andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("[]");
    }
}