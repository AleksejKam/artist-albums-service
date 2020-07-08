package com.aleksejkam.albums.controller;

import com.aleksejkam.albums.entity.Album;
import com.aleksejkam.albums.itunes.ITunesAlbumsResult;
import com.aleksejkam.albums.itunes.ITunesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

        if (topAlbums == null) {
            ITunesAlbumsResult iTunesAlbumsResult = ITunesService.getTopFiveAlbumsBy(amgArtistId);

            List<Album> albums = iTunesAlbumsResult.getResults();

            CopyOnWriteArrayList<Album> finalTopAlbums = new CopyOnWriteArrayList<>();

            albums.stream()
                    .filter(v -> v.isCollection())
                    .forEach(s -> finalTopAlbums.add(s));

            artistTopAlbums.put(amgArtistId, finalTopAlbums);

            return finalTopAlbums;
        }

        return topAlbums.stream()
                .collect(Collectors.toList());
    }
}
