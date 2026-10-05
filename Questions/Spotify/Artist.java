package Spotify;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Artist {
    // each artist will be having many albums, 
    // each albums will be having list of songs,
    private final String id;
    private final String name;

    private final List<Song> songs =new ArrayList<>(); // all the songs
    private final List<Album> albums = new ArrayList<>(); // all the albums

    public Artist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }
}
