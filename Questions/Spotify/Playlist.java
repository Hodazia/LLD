package Spotify;
import java.util.List;
import java.util.ArrayList;

public class Playlist {
    // playlist belongs to a user
    private final String id;
    private final String name;

    private final List<Song> songs = new ArrayList<>();

    public Playlist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public synchronized void addSong(Song song) {
        if (!songs.contains(song)) {
            songs.add(song);
        }
    }

    public synchronized void removeSong(Song song) {
        songs.remove(song);
    }

    public synchronized List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public String getName() {
        return name;
    }
}
