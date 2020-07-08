package com.aleksejkam.albums.controller;

import com.aleksejkam.albums.entity.Album;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TopFiveAlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @RepeatedTest(100)
    public void shouldGetArtistTopAlbums() throws Exception {
        // execute
        MvcResult result = this.mockMvc.perform(get("/artists/3492/top-five-albums")).andDo(print()).andExpect(status().isOk())
                .andReturn();

        // verify
        ObjectMapper mapper = new ObjectMapper();
        List<Album> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Album>>() {});

        assertThat(actual.size()).isEqualTo(5);
        assertThat(actual.get(0).getAmgArtistId()).isEqualTo(3492);
    }
}