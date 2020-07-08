package com.aleksejkam.albums.itunes;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ITunesServiceTest {

    @Test
    void shouldGetTopFiveAlbumsBy() throws IOException {
        // execute
        ITunesAlbumsResult iTunesAlbumsResult = ITunesService.getTopFiveAlbumsBy(3492);

        // verify
        assertThat(iTunesAlbumsResult.getResultCount()).isEqualTo(6);
        assertThat(iTunesAlbumsResult.getResults().get(0).getAmgArtistId()).isEqualTo(3492);
    }
}