package com.aleksejkam.albums.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class FavoriteArtistController {
    private ConcurrentHashMap<Integer, CopyOnWriteArrayList<Integer>> userFavoriteArtists = new ConcurrentHashMap<>();

    @GetMapping("/{userId}/favorite-artists")
    public List<Integer> getUserFavoriteArtists(@PathVariable("userId") Integer userId) {
        CopyOnWriteArrayList<Integer> favoriteArtists = userFavoriteArtists.get(userId);

        if (favoriteArtists == null) {
            return new ArrayList<>();
        }

        return favoriteArtists.stream()
                .collect(Collectors.toList());
    }

    @PostMapping("/{userId}/favorite-artists/{amgArtistId}")
    public ResponseEntity<String> addUserFavoriteArtist(@PathVariable("userId") Integer userId, @PathVariable("amgArtistId") Integer amgArtistId) {
        CopyOnWriteArrayList<Integer> favoriteArtists = userFavoriteArtists.get(userId);

        if (favoriteArtists == null) {
            favoriteArtists = new CopyOnWriteArrayList<>();
        }

        if (!favoriteArtists.contains(amgArtistId)) {
            favoriteArtists.add(amgArtistId);
            userFavoriteArtists.put(userId, favoriteArtists);

            return ResponseEntity.ok("Artist added to favorites of user");
        }

        return ResponseEntity.badRequest().body("Artist is not added, because exist in favorites of user");
    }

    @DeleteMapping("/{userId}/favorite-artists/{amgArtistId}")
    public ResponseEntity<String> removeUserFavoriteArtist(@PathVariable("userId") Integer userId, @PathVariable("amgArtistId") Integer amgArtistId) {
        CopyOnWriteArrayList<Integer> favoriteArtists = userFavoriteArtists.get(userId);

        if (favoriteArtists != null && favoriteArtists.contains(amgArtistId)) {
            favoriteArtists.remove(amgArtistId);

            return ResponseEntity.ok("Artist removed from favorites of user");
        }

        return ResponseEntity.badRequest().body("Artist is not removed, because is not exist in favorites of user");
    }
}
