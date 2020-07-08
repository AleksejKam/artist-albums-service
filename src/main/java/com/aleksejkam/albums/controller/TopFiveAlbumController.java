package com.aleksejkam.albums.controller;

import com.aleksejkam.albums.entity.Album;
import com.aleksejkam.albums.itunes.ITunesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
public class TopFiveAlbumController {
    private ConcurrentHashMap<Integer, CopyOnWriteArrayList<Album>> artistTopAlbums = new ConcurrentHashMap<>();

    @GetMapping("/{amgArtistId}/top-five-albums")
    public List<Album> getArtistTopAlbums(@PathVariable("amgArtistId") Integer amgArtistId) throws IOException {
        CopyOnWriteArrayList<Album> topAlbums = artistTopAlbums.get(amgArtistId);

        if (isEmptyOrRefreshable(topAlbums)) {
            topAlbums = getTopAlbumsFromITunesBy(amgArtistId);
            artistTopAlbums.put(amgArtistId, topAlbums);
        }

        return topAlbums.stream()
                .collect(Collectors.toList());
    }

    private boolean isEmptyOrRefreshable(CopyOnWriteArrayList<Album> topAlbums) {
        if (topAlbums == null) {
            return true;
        }

        Duration lastUpdate = Duration.between(topAlbums.get(0).getLastUpdate(), LocalDateTime.now());

        return lastUpdate.toHours() >= 12;
    }

    private CopyOnWriteArrayList<Album> getTopAlbumsFromITunesBy(Integer amgArtistId) throws IOException {
        List<Album> albums = ITunesService.getTopFiveAlbumsBy(amgArtistId).getResults();

        CopyOnWriteArrayList<Album> finalTopAlbums = new CopyOnWriteArrayList<>();

        albums.stream()
                .filter(Album::isCollection)
                .forEach(finalTopAlbums::add);

        return finalTopAlbums;
    }
}
