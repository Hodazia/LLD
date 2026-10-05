package Spotify;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class User {
    private final String id;
    private final String name;
    private final Set<Song> likedSongs = new HashSet<>();
    private final Set<Artist> followedArtists = new HashSet<>();
    private final List<Playlist> playlists = new ArrayList<>();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public synchronized void likeSong(Song song) {
        likedSongs.add(song);
    }

    public synchronized void unlikeSong(Song song) {
        likedSongs.remove(song);
    }

    public synchronized void followArtist(Artist artist) {
        followedArtists.add(artist);
    }

    public synchronized Playlist createPlaylist(String id,String name) {

        Playlist playlist = new Playlist(id, name);
        playlists.add(playlist);
        return playlist;
    }
    public List<Song> getLikedSongs() {
        return new ArrayList<>(likedSongs);
    }
}
