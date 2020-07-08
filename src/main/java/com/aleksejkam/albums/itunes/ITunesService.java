package com.aleksejkam.albums.itunes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class ITunesService {

    private ITunesService() {
        throw new AssertionError("Suppress default constructor for non-instantiability");
    }

    public static ITunesAlbumsResult getTopFiveAlbumsBy(Integer amgArtistId) {
        String data = requestTopFiveAlbumsBy(amgArtistId);

        return parseAlbumsResult(data);
    }

    private static ITunesAlbumsResult parseAlbumsResult(String data) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ITunesAlbumsResult itunesSearchResult = null;

        try {
            itunesSearchResult = mapper.readValue(data, ITunesAlbumsResult.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itunesSearchResult;
    }

    private static String requestTopFiveAlbumsBy(Integer amgArtistId) {
        final String uri = "https://itunes.apple.com/lookup?amgArtistId={amgArtistId}&entity=album&limit=5";

        Map<String, String> params = new HashMap<>();
        params.put("amgArtistId", amgArtistId.toString());

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(uri, String.class, params);
    }
}
