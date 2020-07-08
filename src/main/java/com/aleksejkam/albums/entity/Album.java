package com.aleksejkam.albums.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties
public class Album implements Serializable {
    private static final long serialVersionUID = 1L;

    private String wrapperType;
    private int amgArtistId;
    private String artistName;
    private String collectionName;
    private String artistViewUrl;
    private LocalDateTime lastUpdate;

    public Album() {
        this.lastUpdate = LocalDateTime.now();
    }

    public String getWrapperType() {
        return wrapperType;
    }

    @JsonIgnore
    public boolean isCollection() {
        return getWrapperType().equals("collection");
    }

    public int getAmgArtistId() {
        return amgArtistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
