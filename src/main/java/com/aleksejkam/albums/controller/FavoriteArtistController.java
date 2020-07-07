package com.aleksejkam.albums.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class FavoriteArtistController {
    private Map<Integer, List<Integer>> userFavoriteArtists = new HashMap<>();

    @GetMapping("/{userId}/favorite-artists")
    public List<Integer> getUserFavoriteArtists(@PathVariable("userId") Integer userId) {
        List<Integer> favoriteArtists = userFavoriteArtists.get(userId);

        if (favoriteArtists == null) {
            return new ArrayList<>();
        }

        return favoriteArtists.stream()
                .collect(Collectors.toList());
    }

    @PostMapping("/{userId}/favorite-artists/{amgArtistId}")
    public ResponseEntity addUserFavoriteArtist(@PathVariable("userId") Integer userId, @PathVariable("amgArtistId") Integer amgArtistId) {
        List<Integer> favoriteArtists = userFavoriteArtists.get(userId);

        if (favoriteArtists == null) {
            favoriteArtists = new ArrayList<>();
        }

        if (!favoriteArtists.contains(amgArtistId)) {
            favoriteArtists.add(amgArtistId);
            userFavoriteArtists.put(userId, favoriteArtists);

            return ResponseEntity.ok("addUserFavoriteArtists");
        }

        return ResponseEntity.badRequest().body("badddd addUserFavoriteArtists");
    }

    @DeleteMapping("/{userId}/favorite-artists/{amgArtistId}")
    public ResponseEntity removeUserFavoriteArtist(@PathVariable("userId") Integer userId, @PathVariable("amgArtistId") Integer amgArtistId) {
        List<Integer> favoriteArtists = userFavoriteArtists.get(userId);

        if (favoriteArtists != null && favoriteArtists.contains(amgArtistId)) {
            favoriteArtists.remove(amgArtistId);

            return ResponseEntity.ok("removeUserFavoriteArtist");
        }

        return ResponseEntity.badRequest().body("badddd removeUserFavoriteArtist");
    }
}
