package Spotify;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Album {
    private final String id;
    private final String title;
    private final Artist artist;

    private final List<Song> songs = new ArrayList<>();
    public Album(
        String id,
        String title,
        Artist artist) {

    this.id = id;
    this.title = title;
    this.artist = artist;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    public String getTitle() {
        return title;
    }

}
