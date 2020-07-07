package com.aleksejkam.albums.itunes;

import com.aleksejkam.albums.entity.Album;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ITunesAlbumsResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private int resultCount;

    private List<Album> results = new ArrayList<Album>();

    public int getResultCount() {
        return resultCount;
    }

    public List<Album> getResults() {
        return results;
    }
}
